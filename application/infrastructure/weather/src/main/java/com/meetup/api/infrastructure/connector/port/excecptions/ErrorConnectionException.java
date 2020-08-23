package com.meetup.api.infrastructure.connector.port.excecptions;

public class ErrorConnectionException extends RuntimeException {
  public ErrorConnectionException(String message) {
    super(message);
  }
}
