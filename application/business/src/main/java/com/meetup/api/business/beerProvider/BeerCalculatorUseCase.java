package com.meetup.api.business.beerProvider;

import com.meetup.api.business.beerProvider.domain.BoxBeerToPurchase;
import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.domain.weather.Temperature;
import java.io.IOException;

public class BeerCalculatorUseCase {
  private final BeerProviderService beerProviderService;
  private final WeatherService weatherService;

  public BeerCalculatorUseCase(
      BeerProviderService beerProviderService, WeatherService weatherService) {
    this.beerProviderService = beerProviderService;
    this.weatherService = weatherService;
  }

  public BoxBeerToPurchase calculateBeersFor(Integer assistants, String where, String when)
      throws IOException, InterruptedException {
    final Temperature temperature = this.weatherService.find(where, when);
    return BoxBeerToPurchase.BoxBeerToPurchaseBuilder.aBoxBeerToPurchase()
        .withPurchased(this.beerProviderService.calculateBeersFor(assistants, temperature))
        .withTemperature(temperature)
        .build();
  }
}
