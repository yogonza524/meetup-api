package com.meetup.api.business.beer.provider;

import static org.junit.jupiter.api.Assertions.*;

import com.meetup.api.domain.weather.Temperature;
import org.junit.jupiter.api.Test;

class SimpleBeerProviderServiceTest {

  private final BeerProviderService beerProviderService = new SimpleBeerProviderService();

  @Test
  void shouldCalculateWhenWeatherIsCold() {
    Long result =
        beerProviderService.calculateBeersFor(
            18, new Temperature.Builder().celciusValue(18.0).create());
    assertEquals(3L, result);
  }

  @Test
  void shouldCalculateWhenWeatherIsFiring() {
    Long result =
        beerProviderService.calculateBeersFor(
            20, new Temperature.Builder().celciusValue(28.0).create());
    assertEquals(10L, result);
  }

  @Test
  void shouldCalculateWhenWeatherIsNormal() {
    Long result =
        beerProviderService.calculateBeersFor(
            8, new Temperature.Builder().celciusValue(22.0).create());
    assertEquals(2L, result);
  }
}
