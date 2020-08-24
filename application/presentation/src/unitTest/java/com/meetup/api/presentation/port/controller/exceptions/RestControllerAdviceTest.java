package com.meetup.api.presentation.port.controller.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RestControllerAdviceTest {

  @Mock private HttpServletRequest request;

  @Mock private Exception ex;

  private RestControllerAdvice restControllerAdvice;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    this.restControllerAdvice = new RestControllerAdvice();
  }

  @Test
  void handleMethodArgumentNotValid() {
    when(ex.getLocalizedMessage()).thenReturn("Invalid parameter");

    var response = restControllerAdvice.handleMethodArgumentNotValid(request, ex);
    assertNotNull(response);
  }

  @Test
  void handleGenericExceptions() {
    when(ex.getLocalizedMessage()).thenReturn("Invalid parameter");

    var response = restControllerAdvice.handleGenericExceptions(request, ex);
    assertNotNull(response);
  }
}
