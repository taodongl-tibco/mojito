package com.box.l10n.mojito.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Entity that describes a Commit entry. This entity mirrors:
 * com.box.l10n.mojito.entity.CommitToPushRun
 *
 * @author garion
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitToPushRun {
  protected Long id;
  protected LocalDateTime createdDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
