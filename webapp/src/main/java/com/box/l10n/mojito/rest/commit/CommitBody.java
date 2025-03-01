package com.box.l10n.mojito.rest.commit;

import com.box.l10n.mojito.entity.Repository;
import java.time.LocalDateTime;

/** @author garion */
public class CommitBody {
  /** {@link Repository#id} */
  Long repositoryId;

  /** The name of the commit (e.g.: commit hash). */
  String commitName;

  /** The commit author's e-mail. */
  String authorEmail;

  /** The commit author's name. */
  String authorName;

  /** The commit date (instead of the author date). */
  LocalDateTime sourceCreationDate;

  public Long getRepositoryId() {
    return repositoryId;
  }

  public void setRepositoryId(Long repositoryId) {
    this.repositoryId = repositoryId;
  }

  public String getCommitName() {
    return commitName;
  }

  public void setCommitName(String commitName) {
    this.commitName = commitName;
  }

  public String getAuthorEmail() {
    return authorEmail;
  }

  public void setAuthorEmail(String authorEmail) {
    this.authorEmail = authorEmail;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public LocalDateTime getSourceCreationDate() {
    return sourceCreationDate;
  }

  public void setSourceCreationDate(LocalDateTime sourceCreationDate) {
    this.sourceCreationDate = sourceCreationDate;
  }
}
