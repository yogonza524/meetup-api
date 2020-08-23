package com.meetup.api.presentation.port.controller.exceptions;

import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
public class RestControllerAdvice {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    HttpMessageNotReadableException.class,
    MethodArgumentNotValidException.class,
    HttpRequestMethodNotSupportedException.class
  })
  protected ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
      HttpServletRequest request, Exception ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Collections.singletonMap("msg", ex.getLocalizedMessage()));
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  protected ResponseEntity<Map<String, Object>> handleGenericExceptions(
      HttpServletRequest request, Exception ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Collections.singletonMap("msg", ex.getLocalizedMessage()));
  }
}
