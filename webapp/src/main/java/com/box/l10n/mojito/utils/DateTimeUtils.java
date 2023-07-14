package com.box.l10n.mojito.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

/** @author jeanaurambault */
@Component
public class DateTimeUtils {

  public LocalDateTime now() {
    return LocalDateTime.now();
  }

  public LocalDateTime now(ZoneId dateTimeZone) {
    return LocalDateTime.now(dateTimeZone);
  }
}
