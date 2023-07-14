package com.box.l10n.mojito.entity;

import com.box.l10n.mojito.rest.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * Similar to {@link AuditableEntity} but allows to override the attributes.
 *
 * <p>Spring doesn't allow to set dates manually via the setter with the annotations and the
 * listener
 */
@MappedSuperclass
public abstract class SettableAuditableEntity extends BaseEntity {

  @Column(name = "created_date")
  @JsonView(View.IdAndNameAndCreated.class)
  protected LocalDateTime createdDate;

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @PrePersist
  public void onPrePersist() {
    if (createdDate == null) {
      createdDate = LocalDateTime.now();
    }
  }
}
