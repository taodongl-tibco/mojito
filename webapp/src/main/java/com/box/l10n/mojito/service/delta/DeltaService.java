package com.box.l10n.mojito.service.delta;

import com.box.l10n.mojito.entity.BaseEntity;
import com.box.l10n.mojito.entity.Locale;
import com.box.l10n.mojito.entity.PullRun;
import com.box.l10n.mojito.entity.PushRun;
import com.box.l10n.mojito.entity.Repository;
import com.box.l10n.mojito.entity.RepositoryLocale;
import com.box.l10n.mojito.service.commit.CommitService;
import com.box.l10n.mojito.service.delta.dtos.DeltaLocaleDataDTO;
import com.box.l10n.mojito.service.delta.dtos.DeltaMetadataDTO;
import com.box.l10n.mojito.service.delta.dtos.DeltaResponseDTO;
import com.box.l10n.mojito.service.delta.dtos.DeltaTranslationDTO;
import com.box.l10n.mojito.service.repository.RepositoryService;
import com.box.l10n.mojito.service.tm.TMTextUnitVariantRepository;
import com.box.l10n.mojito.service.tm.TextUnitVariantDelta;
import com.box.l10n.mojito.service.tm.TextUnitVariantDeltaDTO;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service that enables delta generation.
 *
 * @author garion
 */
@Service
public class DeltaService {

  CommitService commitService;

  RepositoryService repositoryService;

  TMTextUnitVariantRepository tmTextUnitVariantRepository;

  public DeltaService(
      CommitService commitService,
      RepositoryService repositoryService,
      TMTextUnitVariantRepository tmTextUnitVariantRepository) {
    this.commitService = commitService;
    this.repositoryService = repositoryService;
    this.tmTextUnitVariantRepository = tmTextUnitVariantRepository;
  }

  /**
   * Queries the database to identify all Text Unit Variants with creation or update dates newer
   * than the date specified associated with “used” Text Units for the target repo (this should
   * cover all active dev branches)
   *
   * @return The delta of text unit variants, their translations and corresponding metadata.
   */
  public Page<TextUnitVariantDeltaDTO> getDeltasForDates(
      Repository repository,
      List<Locale> locales,
      LocalDateTime fromDate,
      LocalDateTime toDate,
      Pageable pageable) {
    if (locales == null || locales.size() == 0) {
      locales =
          repositoryService.getRepositoryLocalesWithoutRootLocale(repository).stream()
              .map(RepositoryLocale::getLocale)
              .collect(Collectors.toList());
    }

    if (fromDate == null) {
      fromDate = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
    }

    if (toDate == null) {
      toDate = LocalDateTime.now();
    }

    return tmTextUnitVariantRepository.findAllUsedForRepositoryAndLocalesInDateRange(
        repository, locales, fromDate, toDate, pageable);
  }

  /**
   * Queries the database to identify all Text Unit Variants that are newer and different than the
   * variants that were used for the latest PullRuns.
   *
   * <p>To note: for the results to be accurate, each PushRun ID provided should have been processed
   * before it's equivalent PullRun ID.
   *
   * <p>Overall logic looks like this: 1. Using the push_run_ids, collect a list of text units that
   * were associated with the PushRuns 2. Using the pull_run_ids, create a list of the “base”
   * translations that were included for those PullRuns and collect a list of relevant text unit
   * variants that correspond to the text units 3. Compare the “base” text unit variants with the
   * “latest” variants and create a list of results with locale information as well as the type of
   * “translation change” that is being represented in the output (e.g.: “NEW_TRANSLATION” vs.
   * “UPDATED_TRANSLATION”). 4. Filter out any variants that were contained in the previous PullRuns
   * or that have the same contents as before.
   *
   * @return The delta of text unit variants, their translations and corresponding metadata.
   */
  public DeltaResponseDTO getDeltasForRuns(
      Repository repository, List<Locale> locales, List<PushRun> pushRuns, List<PullRun> pullRuns)
      throws PushRunsMissingException {

    if (locales == null || locales.size() == 0) {
      locales =
          repositoryService.getRepositoryLocalesWithoutRootLocale(repository).stream()
              .map(RepositoryLocale::getLocale)
              .collect(Collectors.toList());
    }
    List<Long> localeIds = getIds(locales);

    if (pushRuns == null || pushRuns.size() == 0) {
      throw new PushRunsMissingException("Missing value for pushRuns!");
    }
    List<Long> pushRunIds = getIds(pushRuns);
    List<Long> pullRunIds = getIds(pullRuns);

    LocalDateTime translationsFromDate =
        Optional.ofNullable(pullRuns).orElse(Collections.emptyList()).stream()
            .min(Comparator.comparing(PullRun::getCreatedDate))
            .map(PullRun::getCreatedDate)
            // Remove milliseconds as the Mojito DB does not store dates with sub-second precision.
            .map(dateTime -> dateTime.truncatedTo(ChronoUnit.MILLIS))
            .orElse(LocalDateTime.of(1970, 1, 1, 0, 0));
    Instant fromDateInstant = translationsFromDate.toInstant(ZoneOffset.UTC);
    Timestamp sqlTranslationsFromDate =
        Timestamp.valueOf(LocalDateTime.ofInstant(fromDateInstant, ZoneOffset.UTC));

    List<TextUnitVariantDelta> variants =
        tmTextUnitVariantRepository.findDeltasForRuns(
            repository.getId(), localeIds, pushRunIds, pullRunIds, sqlTranslationsFromDate);

    Map<String, DeltaLocaleDataDTO> deltaLocaleDataByBcp47Tags =
        getStringDeltaLocaleDataDTOMap(variants);

    DeltaMetadataDTO deltaMetadataDTO = new DeltaMetadataDTO();

    DeltaResponseDTO deltaResponseDTO = new DeltaResponseDTO();
    deltaResponseDTO.setTranslationsPerLocale(deltaLocaleDataByBcp47Tags);
    deltaResponseDTO.setDeltaMetadataDTO(deltaMetadataDTO);

    return deltaResponseDTO;
  }

  private List<Long> getIds(List<? extends BaseEntity> baseEntities) {
    return Optional.ofNullable(baseEntities)
        .map(Collection::stream)
        .orElseGet(Stream::empty)
        .map(BaseEntity::getId)
        .collect(Collectors.toList());
  }

  Map<String, DeltaLocaleDataDTO> getStringDeltaLocaleDataDTOMap(
      List<TextUnitVariantDelta> variants) {
    return variants.stream()
        .collect(
            Collectors.groupingBy(
                TextUnitVariantDelta::getBcp47Tag,
                Collectors.collectingAndThen(
                    Collectors.toMap(
                        TextUnitVariantDelta::getTextUnitName,
                        tmTextUnitVariantDelta -> {
                          DeltaTranslationDTO deltaTranslationDTO = new DeltaTranslationDTO();
                          deltaTranslationDTO.setText(tmTextUnitVariantDelta.getContent());
                          deltaTranslationDTO.setDeltaType(tmTextUnitVariantDelta.getDeltaType());
                          return deltaTranslationDTO;
                        }),
                    bcp47TagDeltaTranslationDTOMap -> {
                      DeltaLocaleDataDTO deltaLocaleDataDTO = new DeltaLocaleDataDTO();
                      deltaLocaleDataDTO.setTranslationsByTextUnitName(
                          bcp47TagDeltaTranslationDTOMap);
                      return deltaLocaleDataDTO;
                    })));
  }
}
