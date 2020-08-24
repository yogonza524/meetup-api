package com.meetup.api.domain.asistants;

import java.util.UUID;

public class Assistant {
  private final String uuid = UUID.randomUUID().toString();
  private final String name;
  private final String lastName;
  private final String email;
  private final String mobile;

  public Assistant(String name, String lastName, String email, String mobile) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.mobile = mobile;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }
}
