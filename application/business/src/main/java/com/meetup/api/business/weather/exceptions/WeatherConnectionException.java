package com.meetup.api.business.weather.exceptions;

public class WeatherConnectionException extends RuntimeException {
  private Throwable source;

  public WeatherConnectionException(String message, Throwable source) {
    super(message);
    this.source = source;
  }

  public Throwable getSource() {
    return source;
  }
}
