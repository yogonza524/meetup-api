package com.meetup.api.infrastructure.connector.domain;

import java.io.Serializable;

public class Weather implements Serializable {
  private static final long serialVersionUID = -1790984388559008059L;

  private String city;
  private double celciusValue;
  private String when;

  public String getCity() {
    return city;
  }

  public double getCelciusValue() {
    return celciusValue;
  }

  public String getWhen() {
    return when;
  }

  public static final class WeatherBuilder {
    private String city;
    private double celciusValue;
    private String when;

    private WeatherBuilder() {}

    public static WeatherBuilder aWeather() {
      return new WeatherBuilder();
    }

    public WeatherBuilder withCity(String city) {
      this.city = city;
      return this;
    }

    public WeatherBuilder withCelciusValue(double celciusValue) {
      this.celciusValue = celciusValue;
      return this;
    }

    public WeatherBuilder withWhen(String when) {
      this.when = when;
      return this;
    }

    public Weather build() {
      Weather weather = new Weather();
      weather.celciusValue = this.celciusValue;
      weather.city = this.city;
      weather.when = this.when;
      return weather;
    }
  }
}
