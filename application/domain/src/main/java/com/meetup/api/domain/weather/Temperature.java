package com.meetup.api.domain.weather;

@SuppressWarnings("PMD")
public final class Temperature {
  private final Double CelciusValue;
  private final String City;
  private final String When;

  public Temperature(Double celciusValue, String City, String when) {
    this.CelciusValue = celciusValue;
    this.City = City;
    this.When = when;
  }

  public Double getCelciusValue() {
    return CelciusValue;
  }

  public String getCity() {
    return City;
  }

  public String getWhen() {
    return When;
  }

  public TemperatureRange getRange() {
    final int value = this.CelciusValue.intValue();

    if (value < 20) {
      return TemperatureRange.LESS_THAN_20_CELCIUS;
    }
    if (value > 24) {
      return TemperatureRange.MORE_THAN_24_CELCIUS;
    }

    return TemperatureRange.BETWEEN_20_AND_24_CELCIUS;
  }

  public static class Builder {
    private Double celciusValue;
    private Double latitude;
    private Double longitude;
    private String location;
    private String when;

    public Builder celciusValue(Double celciusValue) {
      this.celciusValue = celciusValue;
      return this;
    }

    public Builder latitude(Double latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(Double longitude) {
      this.longitude = longitude;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Builder when(String when) {
      this.when = when;
      return this;
    }

    public Temperature create() {
      return new Temperature(celciusValue, location, when);
    }
  }
}
