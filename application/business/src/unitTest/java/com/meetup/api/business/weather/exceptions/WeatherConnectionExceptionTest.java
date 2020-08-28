package com.meetup.api.business.weather.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeatherConnectionExceptionTest {

    @Mock
    IOException ioException;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPassWhenExceptionWasShooted() {
        WeatherConnectionException e =
                new WeatherConnectionException("Test", ioException);

        assertNotNull(e.getSource());
    }
}