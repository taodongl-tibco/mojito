package com.box.l10n.mojito.rest.entity;

import java.time.LocalDateTime;

/** @author jaurambault */
public class Drop {

  Long id;

  String name;

  LocalDateTime lastImportedDate;

  Boolean canceled;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getLastImportedDate() {
    return lastImportedDate;
  }

  public void setLastImportedDate(LocalDateTime lastImportedDate) {
    this.lastImportedDate = lastImportedDate;
  }

  public Boolean getCanceled() {
    return canceled;
  }

  public void setCanceled(Boolean canceled) {
    this.canceled = canceled;
  }
}
