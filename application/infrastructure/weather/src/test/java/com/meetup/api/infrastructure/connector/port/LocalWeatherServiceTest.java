package com.meetup.api.infrastructure.connector.port;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.business.weather.exceptions.WeatherConnectionException;
import com.meetup.api.domain.weather.Temperature;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import java.io.IOException;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LocalWeatherServiceTest {

  @Mock private WeatherService weatherService;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void findTemperatureToExternalServiceUsingResilience() throws IOException, InterruptedException {
    when(weatherService.find(any(), any())).thenThrow(new WeatherConnectionException("test", null));

    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("weatherCB");
    Supplier<Temperature> supplier =
        CircuitBreaker.decorateSupplier(circuitBreaker, () -> weatherService.find("", ""));

    Try<Temperature> temperature =
        Try.ofSupplier(supplier)
            .recover(e -> new Temperature.Builder().celciusValue(-255.0).create());

    assertNotNull(temperature);
    assertNotNull(temperature.get());
    assertEquals(-255.0, temperature.get().getCelciusValue());
  }
}
