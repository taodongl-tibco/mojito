package com.box.l10n.mojito.rest.textunit;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts a {@link String} into {@link DateTime}. The string can either be a number of
 * milliseconds from 1970-01-01T00:00:00Z or any format recognized by {@link
 * DateTime#DateTime(java.lang.Object) }
 *
 * @author jeanaurambault
 */
@Component
public class StringToDateTimeConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String source) {

    LocalDateTime converted = null;

    if (source != null) {

      try {
        long instant = Long.parseLong(source);
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(instant), TimeZone.getDefault().toZoneId());
      } catch (NumberFormatException nfe) {
        converted = LocalDateTime.parse(source);
      }
    }

    return converted;
  }
}
