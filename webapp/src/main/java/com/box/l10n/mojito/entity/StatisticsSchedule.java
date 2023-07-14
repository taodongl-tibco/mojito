package com.box.l10n.mojito.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity to keep track of which repository's statistics are outdated.
 *
 * @author jyi
 */
@Entity
@Table(
    name = "statistics_schedule",
    indexes = {
      @Index(
          name = "I__STATISTICS_SCHEDULE__REPOSITORY_ID",
          columnList = "repository_id",
          unique = false)
    })
public class StatisticsSchedule extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(
      name = "repository_id",
      foreignKey = @ForeignKey(name = "FK__STATISTICS_SCHEDULE__REPOSITORY_ID"))
  private Repository repository;

  @Column(name = "time_to_update")
  protected LocalDateTime timeToUpdate;

  public Repository getRepository() {
    return repository;
  }

  public void setRepository(Repository repository) {
    this.repository = repository;
  }

  public LocalDateTime getTimeToUpdate() {
    return timeToUpdate;
  }

  public void setTimeToUpdate(LocalDateTime timeToUpdate) {
    this.timeToUpdate = timeToUpdate;
  }
}
