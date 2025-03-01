package com.box.l10n.mojito.service.tm.search;

import com.box.l10n.mojito.entity.TMTextUnitVariant;
import java.time.LocalDateTime;

/**
 * DTO to build text units from TM entities
 *
 * @author jaurambault
 */
public class TextUnitDTO {

  private Long tmTextUnitId;
  private Long tmTextUnitVariantId;
  private Long localeId;
  private String name;
  private String source;
  private String comment;
  private String target;
  private String targetLocale;
  private String targetComment;
  private Long assetId;
  private Long lastSuccessfulAssetExtractionId;
  private Long assetExtractionId;
  private Long tmTextUnitCurrentVariantId;
  private TMTextUnitVariant.Status status;
  private boolean includedInLocalizedFile;
  private LocalDateTime createdDate;
  private boolean assetDeleted;
  private String pluralForm;
  private String pluralFormOther;
  private String repositoryName;
  private String assetPath;
  private Long assetTextUnitId;
  private LocalDateTime tmTextUnitCreatedDate;
  private boolean doNotTranslate;

  public Long getTmTextUnitId() {
    return tmTextUnitId;
  }

  public void setTmTextUnitId(Long tmTextUnitId) {
    this.tmTextUnitId = tmTextUnitId;
  }

  public Long getTmTextUnitVariantId() {
    return tmTextUnitVariantId;
  }

  public void setTmTextUnitVariantId(Long tmTextUnitVariantId) {
    this.tmTextUnitVariantId = tmTextUnitVariantId;
  }

  public Long getLocaleId() {
    return localeId;
  }

  // TODO(PO) THIS NOT CONSISTANT !! chooose
  public void setLocaleId(Long localeId) {
    this.localeId = localeId;
  }

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
    this.source = source;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  // TODO(PO) THIS NOT CONSISTANT !! chooose
  public String getTargetLocale() {
    return targetLocale;
  }

  public void setTargetLocale(String targetLocale) {
    this.targetLocale = targetLocale;
  }

  public Long getAssetId() {
    return assetId;
  }

  public void setAssetId(Long assetId) {
    this.assetId = assetId;
  }

  public boolean isUsed() {
    return !assetDeleted
        && assetExtractionId != null
        && assetExtractionId.equals(lastSuccessfulAssetExtractionId);
  }

  public boolean isTranslated() {
    return tmTextUnitVariantId != null;
  }

  public boolean hasEmptyTranslation() {
    return target == null || target.isEmpty();
  }

  public Long getLastSuccessfulAssetExtractionId() {
    return lastSuccessfulAssetExtractionId;
  }

  public void setLastSuccessfulAssetExtractionId(Long lastSuccessfulAssetExtractionId) {
    this.lastSuccessfulAssetExtractionId = lastSuccessfulAssetExtractionId;
  }

  public Long getAssetExtractionId() {
    return assetExtractionId;
  }

  public void setAssetExtractionId(Long assetExtractionId) {
    this.assetExtractionId = assetExtractionId;
  }

  public String getTargetComment() {
    return targetComment;
  }

  public void setTargetComment(String targetComment) {
    this.targetComment = targetComment;
  }

  public Long getTmTextUnitCurrentVariantId() {
    return tmTextUnitCurrentVariantId;
  }

  public void setTmTextUnitCurrentVariantId(Long tmTextUnitCurrentVariantId) {
    this.tmTextUnitCurrentVariantId = tmTextUnitCurrentVariantId;
  }

  public TMTextUnitVariant.Status getStatus() {
    return status;
  }

  public void setStatus(TMTextUnitVariant.Status status) {
    this.status = status;
  }

  public boolean isIncludedInLocalizedFile() {
    return includedInLocalizedFile;
  }

  public void setIncludedInLocalizedFile(boolean includedInLocalizedFile) {
    this.includedInLocalizedFile = includedInLocalizedFile;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public boolean isAssetDeleted() {
    return assetDeleted;
  }

  public void setAssetDeleted(boolean assetDeleted) {
    this.assetDeleted = assetDeleted;
  }

  public String getPluralForm() {
    return pluralForm;
  }

  public void setPluralForm(String pluralForm) {
    this.pluralForm = pluralForm;
  }

  public String getPluralFormOther() {
    return pluralFormOther;
  }

  public void setPluralFormOther(String pluralFormOther) {
    this.pluralFormOther = pluralFormOther;
  }

  public String getRepositoryName() {
    return repositoryName;
  }

  public void setRepositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
  }

  public String getAssetPath() {
    return assetPath;
  }

  public void setAssetPath(String assetPath) {
    this.assetPath = assetPath;
  }

  public Long getAssetTextUnitId() {
    return assetTextUnitId;
  }

  public void setAssetTextUnitId(Long assetTextUnitId) {
    this.assetTextUnitId = assetTextUnitId;
  }

  public LocalDateTime getTmTextUnitCreatedDate() {
    return tmTextUnitCreatedDate;
  }

  public void setTmTextUnitCreatedDate(LocalDateTime tmTextUnitCreatedDate) {
    this.tmTextUnitCreatedDate = tmTextUnitCreatedDate;
  }

  public boolean isDoNotTranslate() {
    return doNotTranslate;
  }

  public void setDoNotTranslate(boolean doNotTranslate) {
    this.doNotTranslate = doNotTranslate;
  }
}
