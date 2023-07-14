package com.box.l10n.mojito.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity that describes a PushRun. This entity mirrors: com.box.l10n.mojito.entity.PushRun
 *
 * @author garion
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushRun {
  protected Long id;
  protected LocalDateTime createdDate;

  @JsonProperty("repository_id")
  private Long repositoryId;

  private String name;

  @JsonManagedReference private Set<PushRunAsset> pushRunAssets;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRepositoryId() {
    return repositoryId;
  }

  public void setRepositoryId(Long repositoryId) {
    this.repositoryId = repositoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Set<PushRunAsset> getPushRunAssets() {
    return pushRunAssets;
  }

  public void setPushRunAssets(Set<PushRunAsset> pushRunAssetSet) {
    this.pushRunAssets = pushRunAssetSet;
  }
}
