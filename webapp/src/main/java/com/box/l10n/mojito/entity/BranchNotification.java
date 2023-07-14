package com.box.l10n.mojito.entity;

import com.box.l10n.mojito.rest.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/** @author jeanaurambault */
@Entity
@Table(
    name = "branch_notification",
    indexes = {
      @Index(
          name = "UK__BRANCH_NOTIFICATION__BRANCH_ID__SENDER_TYPE",
          columnList = "branch_id, sender_type",
          unique = true),
    })
public class BranchNotification extends BaseEntity {

  @JsonView(View.BranchStatistic.class)
  @ManyToOne(optional = false)
  @JoinColumn(
      name = "branch_id",
      foreignKey = @ForeignKey(name = "FK__BRANCH_NOTIFICATION__BRANCH__ID"))
  private Branch branch;

  @Column(name = "new_msg_sent_at")
  LocalDateTime newMsgSentAt;

  @Column(name = "updated_msg_sent_at")
  LocalDateTime updatedMsgSentAt;

  @Column(name = "screenshot_missing_msg_sent_at")
  LocalDateTime screenshotMissingMsgSentAt;

  @Column(name = "translated_msg_sent_at")
  LocalDateTime translatedMsgSentAt;

  @Column(name = "content_md5")
  String contentMD5;

  @Column(name = "message_id")
  String messageId;

  @Column(name = "sender_type")
  String senderType;

  @Column(name = "notifier_id")
  String notifierId;

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public LocalDateTime getNewMsgSentAt() {
    return newMsgSentAt;
  }

  public void setNewMsgSentAt(LocalDateTime newMsgSentAt) {
    this.newMsgSentAt = newMsgSentAt;
  }

  public LocalDateTime getUpdatedMsgSentAt() {
    return updatedMsgSentAt;
  }

  public void setUpdatedMsgSentAt(LocalDateTime updatedMsgSentAt) {
    this.updatedMsgSentAt = updatedMsgSentAt;
  }

  public LocalDateTime getScreenshotMissingMsgSentAt() {
    return screenshotMissingMsgSentAt;
  }

  public void setScreenshotMissingMsgSentAt(LocalDateTime screenshotMissingMsgSentAt) {
    this.screenshotMissingMsgSentAt = screenshotMissingMsgSentAt;
  }

  public LocalDateTime getTranslatedMsgSentAt() {
    return translatedMsgSentAt;
  }

  public void setTranslatedMsgSentAt(LocalDateTime translatedMsgSentAt) {
    this.translatedMsgSentAt = translatedMsgSentAt;
  }

  public String getContentMD5() {
    return contentMD5;
  }

  public void setContentMD5(String contentMD5) {
    this.contentMD5 = contentMD5;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getSenderType() {
    return senderType;
  }

  public void setSenderType(String senderType) {
    this.senderType = senderType;
  }

  public String getNotifierId() {
    return notifierId;
  }

  public void setNotifierId(String notifierId) {
    this.notifierId = notifierId;
  }
}
