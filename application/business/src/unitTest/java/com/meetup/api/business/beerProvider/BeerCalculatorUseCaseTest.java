package com.meetup.api.business.beerProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.domain.weather.Temperature;
import java.io.IOException;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BeerCalculatorUseCaseTest {

  @Mock private WeatherService weatherService;
  @Mock private BeerProviderService beerProviderService;

  private BeerCalculatorUseCase beerCalculatorUseCase;
  private final Temperature temperature = new Temperature.Builder().celciusValue(18.0).create();

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.initMocks(this);
    this.beerCalculatorUseCase = new BeerCalculatorUseCase(beerProviderService, weatherService);
  }

  @Test
  void calculateBeersFor() throws IOException, InterruptedException {
    when(weatherService.find(any(), any())).thenReturn(temperature);

    when(beerProviderService.calculateBeersFor(anyInt(), any())).thenReturn(2L);

    Long purchased = beerCalculatorUseCase.calculateBeersFor(10, "Chaco", new Date());
    assertEquals(2L, purchased);
  }
}
