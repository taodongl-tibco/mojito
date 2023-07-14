package com.box.l10n.mojito.service.sla;

import com.box.l10n.mojito.utils.DateTimeUtils;
import com.google.common.collect.Sets;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** @author jeanaurambault */
@Component
public class DropScheduleService {

  static Logger logger = LoggerFactory.getLogger(DropScheduleService.class);

  @Autowired DropScheduleConfig dropScheduleConfig;

  @Autowired DateTimeUtils dateTimeUtils;

  public LocalDateTime getLastDropCreatedDate() {
    LocalDateTime now = dateTimeUtils.now(dropScheduleConfig.getTimezone());
    LocalDateTime lastDropDueDate = getLastDropDueDate(now);
    return getDropCreatedDate(lastDropDueDate);
  }

  LocalDateTime getDropCreatedDate(LocalDateTime dropDueDate) {
    LocalDateTime dropCreatedDate =
        dropDueDate
            .withHour(dropScheduleConfig.getCreatedLocalTime().getHour())
            .withMinute(dropScheduleConfig.getCreatedLocalTime().getMinute())
            .withSecond(dropScheduleConfig.getCreatedLocalTime().getSecond());

    int dropDueDateDay = dropDueDate.getDayOfWeek().getValue();
    int dropStartDateDay = getDueDayToStartDay().get(dropDueDateDay);

    dropCreatedDate = dropCreatedDate.with(DayOfWeek.of(dropStartDateDay));

    if (dropStartDateDay > dropDueDateDay) {
      dropCreatedDate = dropCreatedDate.minusWeeks(1);
    }

    return dropCreatedDate;
  }

  LocalDateTime getLastDropDueDate(LocalDateTime before) {

    LocalDateTime lastDropDueDate = null;

    HashSet<Integer> dropDueDaysSet = Sets.newHashSet(dropScheduleConfig.getDueDays());

    for (int daysToSubstract = 0; daysToSubstract <= 7; daysToSubstract++) {
      LocalDateTime candidate =
          before
              .minusDays(daysToSubstract)
              .withHour(dropScheduleConfig.getDueLocalTime().getHour())
              .withMinute(dropScheduleConfig.getDueLocalTime().getMinute())
              .withSecond(dropScheduleConfig.getDueLocalTime().getSecond());

      if (dropDueDaysSet.contains(candidate.getDayOfWeek().getValue())
          && !candidate.isAfter(before)) {
        lastDropDueDate = candidate;
        break;
      }
    }

    return lastDropDueDate;
  }

  Map<Integer, Integer> getDueDayToStartDay() {
    Map<Integer, Integer> dueDayToStartDay = new HashMap<>();

    for (int i = 0; i < dropScheduleConfig.getDueDays().size(); i++) {
      dueDayToStartDay.put(
          dropScheduleConfig.getDueDays().get(i), dropScheduleConfig.getCreatedDays().get(i));
    }

    return dueDayToStartDay;
  }
}
