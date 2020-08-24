package com.meetup.api.business.beer.provider;

import com.meetup.api.domain.weather.Temperature;

public interface BeerProviderService {
  Long calculateBeersFor(Integer assistants, Temperature temperature);
}
