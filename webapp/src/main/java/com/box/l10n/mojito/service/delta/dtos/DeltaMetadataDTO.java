package com.box.l10n.mojito.service.delta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * Metadata for the Delta response.
 *
 * @author garion
 */
public class DeltaMetadataDTO {

  /**
   * This records the start date from which point we would have captured deltas from, when deltas
   * where created based on a date range.
   */
  @JsonProperty("fromDate")
  public LocalDateTime fromDate;

  /**
   * This records the end date up to which point we would have captured deltas to, when deltas where
   * created based on a date range.
   */
  @JsonProperty("toDate")
  public LocalDateTime toDate;

  public LocalDateTime getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDateTime fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDateTime getToDate() {
    return toDate;
  }

  public void setToDate(LocalDateTime toDate) {
    this.toDate = toDate;
  }
}
