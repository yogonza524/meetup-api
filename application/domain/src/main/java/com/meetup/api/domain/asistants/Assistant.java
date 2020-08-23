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
}
