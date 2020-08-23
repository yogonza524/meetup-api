package com.meetup.api.business.weather;

import com.meetup.api.domain.weather.Temperature;
import java.io.IOException;

public interface WeatherService {
  Temperature find(String place, String when) throws IOException, InterruptedException;
}
