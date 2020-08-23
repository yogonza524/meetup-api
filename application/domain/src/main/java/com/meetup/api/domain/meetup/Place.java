package com.meetup.api.domain.meetup;

import java.util.UUID;

public final class Place {
  private final String uuid = UUID.randomUUID().toString();

  private final String name;
  private final String address;
  private final String city;
  private final String country;

  public Place(String name, String address, String city, String country) {
    this.name = name;
    this.address = address;
    this.city = city;
    this.country = country;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public static class Builder {
    private String name;
    private String address;
    private String city;
    private String country;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder adddress(String address) {
      this.address = address;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Place create() {
      return new Place(name, address, city, country);
    }
  }
}
