package com.meetup.api.business.beerProvider;

import com.meetup.api.domain.weather.Temperature;

public interface BeerProviderService {
  Long calculateBeersFor(Integer assistants, Temperature temperature);
}
