package com.box.l10n.mojito.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/** @author jaurambault */
@Entity
@Table(name = "sla_incident")
public class SlaIncident extends AuditableEntity {

  @Column(name = "closed_date")
  private LocalDateTime closedDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "sla_incident_repositories",
      joinColumns = {@JoinColumn(name = "sla_incident_id")},
      inverseJoinColumns = {@JoinColumn(name = "repository_id")},
      foreignKey = @ForeignKey(name = "FK__SLA_INCIDENT_REPOSITORIES__SLA_INCIDENT__ID"),
      inverseForeignKey = @ForeignKey(name = "FK__SLA_INCIDENT_REPOSITORIES__REPOSITORY__ID"))
  private Set<Repository> repositories;

  public Set<Repository> getRepositories() {
    return repositories;
  }

  public void setRepositories(Set<Repository> repositories) {
    this.repositories = repositories;
  }

  public LocalDateTime getClosedDate() {
    return closedDate;
  }

  public void setClosedDate(LocalDateTime closedDate) {
    this.closedDate = closedDate;
  }
}
