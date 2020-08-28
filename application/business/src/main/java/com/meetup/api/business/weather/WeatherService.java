package com.meetup.api.business.weather;

import com.meetup.api.business.weather.exceptions.WeatherConnectionException;
import com.meetup.api.domain.weather.Temperature;

public interface WeatherService {
  Temperature find(String place, String when) throws WeatherConnectionException;
}
