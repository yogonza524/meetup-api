package com.meetup.api.presentation.port.trace;

import com.meetup.api.business.weather.exceptions.WeatherConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TraceConfigurationTest {

    private TraceConfiguration traceConfiguration;

    @Mock
    private Logger logger;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        traceConfiguration = new TraceConfiguration();
        when(logger.isInfoEnabled()).thenReturn(true);
        when(logger.isErrorEnabled()).thenReturn(true);
        when(logger.isWarnEnabled()).thenReturn(true);
        when(logger.isErrorEnabled()).thenReturn(true);

        ReflectionTestUtils.setField(traceConfiguration, "logger", logger);
    }

    @Test
    void beforeCalculateBeersFor() {
        traceConfiguration.beforeCalculateBeersFor(null, 12, "Chaco", "2020-09-12");

        verify(logger, times(1))
                .info(any());
    }

    @Test
    void beforeRestBeerCalculate() {
        traceConfiguration.beforeRestBeerCalculate(null, 12, "Chaco", "2020-09-12");

        verify(logger, times(1))
                .info(any());
    }

    @Test
    void beforeBadRequestException() {
        traceConfiguration.beforeBadRequestException(null, null, new RuntimeException("Test Error"));

        verify(logger, times(1))
                .warn(any());
    }

    @Test
    void beforeHandleWeatherConnectionExceptionWithSource() {
        WeatherConnectionException we = new WeatherConnectionException("",
                new RuntimeException("Error"));
        //when(we.getSource().getLocalizedMessage()).thenReturn("IOError");

        traceConfiguration.beforeHandleWeatherConnectionExceptions(null, null, we);

        verify(logger, times(1))
                .error(any());
    }

    @Test
    void beforeHandleWeatherConnectionExceptionWithoutSource() {
        Exception we = new RuntimeException("ERROR");
        //when(we.getSource().getLocalizedMessage()).thenReturn("IOError");

        traceConfiguration.beforeHandleWeatherConnectionExceptions(null, null, we);

        verify(logger, times(1))
                .error(any());
    }
}