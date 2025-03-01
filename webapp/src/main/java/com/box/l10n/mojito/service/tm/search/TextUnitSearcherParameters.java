package com.box.l10n.mojito.service.tm.search;

import com.box.l10n.mojito.service.NormalizationUtils;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Parameters for {@link
 * TextUnitSearcher#search(com.box.l10n.mojito.service.tm.search.TextUnitSearcherParameters) }
 *
 * @author jaurambault
 */
public class TextUnitSearcherParameters {

  String name;
  String source;
  String target;
  String assetPath;
  String pluralFormOther;
  SearchType searchType;
  List<Long> repositoryIds;
  List<String> repositoryNames;
  List<Long> tmTextUnitIds;
  List<String> localeTags;
  Long localeId;
  UsedFilter usedFilter;
  StatusFilter statusFilter;
  Integer offset;
  Integer limit;
  Long assetId;
  Long tmId;
  String md5;
  boolean forRootLocale = false;
  boolean rootLocaleExcluded = true;
  Boolean toBeFullyTranslatedFilter;
  boolean untranslatedOrTranslationNeeded = false;
  boolean pluralFormsFiltered = true;
  boolean pluralFormsExcluded = false;
  Long pluralFormId;
  Boolean doNotTranslateFilter;
  LocalDateTime tmTextUnitCreatedBefore;
  LocalDateTime tmTextUnitCreatedAfter;
  Long branchId;
  String skipTextUnitWithPattern;
  String includeTextUnitsWithPattern;
  String skipAssetPathWithPattern;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = NormalizationUtils.normalize(source);
  }

  public SearchType getSearchType() {
    return searchType;
  }

  public void setSearchType(SearchType searchType) {
    this.searchType = searchType;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = NormalizationUtils.normalize(target);
  }

  public String getAssetPath() {
    return assetPath;
  }

  public void setAssetPath(String assetPath) {
    this.assetPath = assetPath;
  }

  public List<Long> getRepositoryIds() {
    return repositoryIds;
  }

  public void setRepositoryIds(Long repositoryId) {
    this.repositoryIds = Arrays.asList(repositoryId);
  }

  public void setRepositoryIds(List<Long> repositoryIds) {
    this.repositoryIds = repositoryIds;
  }

  public List<String> getLocaleTags() {
    return localeTags;
  }

  public void setLocaleTags(List<String> localeTags) {
    this.localeTags = localeTags;
  }

  public Long getLocaleId() {
    return localeId;
  }

  public void setLocaleId(Long localeId) {
    this.localeId = localeId;
  }

  public UsedFilter getUsedFilter() {
    return usedFilter;
  }

  public void setUsedFilter(UsedFilter usedFilter) {
    this.usedFilter = usedFilter;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Long getAssetId() {
    return assetId;
  }

  public void setAssetId(Long assetId) {
    this.assetId = assetId;
  }

  public Long getTmId() {
    return tmId;
  }

  public void setTmId(Long tmId) {
    this.tmId = tmId;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public boolean isRootLocaleExcluded() {
    return rootLocaleExcluded;
  }

  public void setRootLocaleExcluded(boolean rootLocaleExcluded) {
    this.rootLocaleExcluded = rootLocaleExcluded;
  }

  public boolean isForRootLocale() {
    return forRootLocale;
  }

  public void setForRootLocale(boolean forRootLocale) {
    this.forRootLocale = forRootLocale;
  }

  public Boolean getToBeFullyTranslatedFilter() {
    return toBeFullyTranslatedFilter;
  }

  public void setToBeFullyTranslatedFilter(Boolean toBeFullyTranslatedFilter) {
    this.toBeFullyTranslatedFilter = toBeFullyTranslatedFilter;
  }

  public StatusFilter getStatusFilter() {
    return statusFilter;
  }

  public void setStatusFilter(StatusFilter statusFilter) {
    this.statusFilter = statusFilter;
  }

  public String getPluralFormOther() {
    return pluralFormOther;
  }

  public void setPluralFormOther(String pluralFormOther) {
    this.pluralFormOther = pluralFormOther;
  }

  public List<String> getRepositoryNames() {
    return repositoryNames;
  }

  public void setRepositoryNames(List<String> repositoryNames) {
    this.repositoryNames = repositoryNames;
  }

  public boolean isPluralFormsFiltered() {
    return pluralFormsFiltered;
  }

  public void setPluralFormsFiltered(boolean pluralFormsFiltered) {
    this.pluralFormsFiltered = pluralFormsFiltered;
  }

  public Long getPluralFormId() {
    return pluralFormId;
  }

  public void setPluralFormId(Long pluralFormId) {
    this.pluralFormId = pluralFormId;
  }

  public boolean isPluralFormsExcluded() {
    return pluralFormsExcluded;
  }

  public void setPluralFormsExcluded(boolean pluralFormsExcluded) {
    this.pluralFormsExcluded = pluralFormsExcluded;
  }

  public Boolean getDoNotTranslateFilter() {
    return doNotTranslateFilter;
  }

  public void setDoNotTranslateFilter(Boolean doNotTranslateFilter) {
    this.doNotTranslateFilter = doNotTranslateFilter;
  }

  public LocalDateTime getTmTextUnitCreatedBefore() {
    return tmTextUnitCreatedBefore;
  }

  public void setTmTextUnitCreatedBefore(LocalDateTime tmTextUnitCreatedBefore) {
    this.tmTextUnitCreatedBefore = tmTextUnitCreatedBefore;
  }

  public LocalDateTime getTmTextUnitCreatedAfter() {
    return tmTextUnitCreatedAfter;
  }

  public void setTmTextUnitCreatedAfter(LocalDateTime tmTextUnitCreatedAfter) {
    this.tmTextUnitCreatedAfter = tmTextUnitCreatedAfter;
  }

  public Long getBranchId() {
    return branchId;
  }

  public void setBranchId(Long branchId) {
    this.branchId = branchId;
  }

  public List<Long> getTmTextUnitIds() {
    return tmTextUnitIds;
  }

  public void setTmTextUnitIds(List<Long> tmTextUnitIds) {
    this.tmTextUnitIds = tmTextUnitIds;
  }

  public void setTmTextUnitIds(Long... tmTextUnitIds) {
    List<Long> filtered =
        Arrays.stream(tmTextUnitIds).filter(Objects::nonNull).collect(Collectors.toList());

    if (!filtered.isEmpty()) {
      this.tmTextUnitIds = filtered;
    }
  }

  public String getSkipTextUnitWithPattern() {
    return skipTextUnitWithPattern;
  }

  public void setSkipTextUnitWithPattern(String skipTextUnitWithPattern) {
    this.skipTextUnitWithPattern = skipTextUnitWithPattern;
  }

  public String getIncludeTextUnitsWithPattern() {
    return includeTextUnitsWithPattern;
  }

  public void setIncludeTextUnitsWithPattern(String includeTextUnitsWithPattern) {
    this.includeTextUnitsWithPattern = includeTextUnitsWithPattern;
  }

  public String getSkipAssetPathWithPattern() {
    return skipAssetPathWithPattern;
  }

  public void setSkipAssetPathWithPattern(String skipAssetPathWithPattern) {
    this.skipAssetPathWithPattern = skipAssetPathWithPattern;
  }
}
