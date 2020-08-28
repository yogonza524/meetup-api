package com.meetup.api.presentation.port.trace;

import com.meetup.api.business.weather.exceptions.WeatherConnectionException;
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
      "execution(* com.meetup.api.business.beer.provider.BeerCalculatorUseCase.calculateBeersFor(..)) && args(assistants,where,when,..)")
  public void beforeCalculateBeersFor(
      JoinPoint joinPoint, int assistants, String where, String when) {
    // Advice
    if (logger.isInfoEnabled()) {
      logger.info(
          "action=calculateBeersFor, assistants="
              + assistants
              + ", where="
              + where
              + ", when="
              + when);
    }
  }

  @Before(
      "execution(* com.meetup.api.presentation.port.controller.BeerController.beerCalculate(..)) && args(assistants,where,date,..)")
  public void beforeRestBeerCalculate(
      JoinPoint joinPoint, int assistants, String where, String date) {
    // Advice
    if (logger.isInfoEnabled()) {
      logger.info(
          "action=beerCalculateRest, assistants="
              + assistants
              + ", where="
              + where
              + ", when="
              + date);
    }
  }

  @Before(
      "execution(* com.meetup.api.presentation.port.controller.exceptions.RestControllerAdvice.handleGenericExceptions(..)) && args(request,ex,..)")
  public void beforeBadRequestException(
      JoinPoint joinPoint, HttpServletRequest request, Exception ex) {
    // Advice
    if (logger.isWarnEnabled()) {
      logger.warn("action=handleGenericExceptions, exception=" + ex.getLocalizedMessage());
    }
  }

  @Before(
      "execution(* com.meetup.api.presentation.port.controller.exceptions.RestControllerAdvice.handleWeatherConnectionExceptions(..)) && args(request,ex,..)")
  public void beforeHandleWeatherConnectionExceptions(
      JoinPoint joinPoint, HttpServletRequest request, Exception ex) {
    // Advice
    if (logger.isErrorEnabled()) {
      if (ex instanceof WeatherConnectionException) {
        String cause = ((WeatherConnectionException) ex).getSource().getLocalizedMessage();
        logger.error(
            "action=handleWeatherConnectionExceptions, exception="
                + ex.getLocalizedMessage()
                + ", source="
                + cause);
      } else {
        logger.error(
            "action=handleWeatherConnectionExceptions, exception=" + ex.getLocalizedMessage());
      }
    }
  }
}
