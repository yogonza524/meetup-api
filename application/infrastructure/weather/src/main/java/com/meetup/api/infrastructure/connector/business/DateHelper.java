package com.meetup.api.infrastructure.connector.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
  private static final SimpleDateFormat formatter =
      new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

  public static Date from(String dateString) throws ParseException {
    return formatter.parse(dateString);
  }
}
