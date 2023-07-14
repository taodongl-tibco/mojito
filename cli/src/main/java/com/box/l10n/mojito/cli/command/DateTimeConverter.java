package com.box.l10n.mojito.cli.command;

import com.beust.jcommander.IStringConverter;
import java.time.LocalDateTime;

public class DateTimeConverter implements IStringConverter<LocalDateTime> {
  @Override
  public LocalDateTime convert(String dateAsText) {
    return LocalDateTime.parse(dateAsText);
  }
}
