/**
 * Licensed Materials - Property of IBM
 * IBM Digital Health Pass PID-TBD
 * (c) Copyright IBM Corporation 2021  All Rights Reserved.
 */

package io.github.iliecirciumaru.dcc.rules.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RFC339DateSerializer {
  private static final String RFC3339_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(RFC3339_DATE_FORMAT).withZone(ZoneId.of("UTC"));

  public static String formatString(ZonedDateTime value) {
    if (value == null) {
      return null;
    }

    return dateTimeFormatter.format(value);
  }
}
