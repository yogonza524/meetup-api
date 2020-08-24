package com.meetup.api.business.beer.provider;

import com.meetup.api.domain.beer.consume.BeerFactorPerAssistant;
import com.meetup.api.domain.beer.provider.BeerBoxSelled;
import com.meetup.api.domain.weather.Temperature;

public class SimpleBeerProviderService implements BeerProviderService {

  @Override
  public Long calculateBeersFor(Integer assistants, Temperature temperature) {
    double totalConsumed = 0.0;
    switch (temperature.getRange()) {
      case LESS_THAN_20_CELCIUS:
        totalConsumed =
            Math.ceil((double) assistants * BeerFactorPerAssistant.THREE_QUARTERS.value());
        break;
      case BETWEEN_20_AND_24_CELCIUS:
        totalConsumed = assistants;
        break;
      case MORE_THAN_24_CELCIUS:
        totalConsumed = assistants * (1 + BeerFactorPerAssistant.TWO.value());
        break;
    }
    return (long) Math.ceil(totalConsumed / BeerBoxSelled.SIX_PER_PURCHASE.value());
  }
}
