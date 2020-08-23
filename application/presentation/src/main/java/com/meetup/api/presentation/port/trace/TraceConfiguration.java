package com.meetup.api.presentation.port.trace;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class TraceConfiguration {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Before(
      "execution(* com.meetup.api.business.beerProvider.BeerCalculatorUseCase.calculateBeersFor(..)) && args(assistants,where,when,..)")
  public void beforeCalculateBeersFor(
      JoinPoint joinPoint, int assistants, String where, String when) {
    // Advice
    logger.info(
        "action=calculateBeersFor, assistants="
            + assistants
            + ", where="
            + where
            + ", when="
            + when);
  }

  @Before(
      "execution(* com.meetup.api.presentation.port.controller.BeerController.beerCalculate(..)) && args(assistants,where,date,..)")
  public void beforeRestBeerCalculate(
      JoinPoint joinPoint, int assistants, String where, String date) {
    // Advice
    logger.info(
        "action=beerCalculateRest, assistants="
            + assistants
            + ", where="
            + where
            + ", when="
            + date);
  }

  @Before(
      "execution(* com.meetup.api.presentation.port.controller.exceptions.RestControllerAdvice.handleGenericExceptions(..)) && args(request,ex,..)")
  public void beforeBadRequestException(
      JoinPoint joinPoint, HttpServletRequest request, Exception ex) {
    // Advice
    logger.info("action=handleGenericExceptions, exception="
            + ex.getLocalizedMessage());
  }
}
