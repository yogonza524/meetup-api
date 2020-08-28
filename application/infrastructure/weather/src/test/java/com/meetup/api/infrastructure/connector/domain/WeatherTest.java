package com.meetup.api.infrastructure.connector.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    private Weather weather;
    private final String CITY = "Chaco";
    private final String WHEN = "2020-12-03";
    private final double TEMPERATURE = 22.0;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        weather = Weather.WeatherBuilder.aWeather()
                .withCelciusValue(TEMPERATURE)
                .withCity(CITY)
                .withWhen(WHEN)
                .build();
    }

    @Test
    void getCity() {
        assertEquals(CITY, weather.getCity());
    }

    @Test
    void getCelciusValue() {
        assertEquals(TEMPERATURE, weather.getCelciusValue());
    }

    @Test
    void getWhen() {
        assertEquals(WHEN, weather.getWhen());
    }
}