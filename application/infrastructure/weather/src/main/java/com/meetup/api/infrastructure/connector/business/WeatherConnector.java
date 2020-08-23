package com.meetup.api.infrastructure.connector.business;

import com.meetup.api.infrastructure.connector.domain.Weather;
import java.io.IOException;

public interface WeatherConnector {
  Weather findByCityAndDate(String city, String when) throws IOException, InterruptedException;
}
