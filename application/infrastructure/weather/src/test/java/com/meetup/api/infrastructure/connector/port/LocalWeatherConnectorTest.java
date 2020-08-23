package com.meetup.api.infrastructure.connector.port;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.meetup.api.domain.weather.Temperature;
import com.meetup.api.infrastructure.connector.business.DateHelper;
import java.io.IOException;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LocalWeatherConnectorTest {

  @Mock private LocalWeatherConnector localWeatherConnector;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void findByCityAndDate() throws IOException, InterruptedException, ParseException {
    final String CITY = "Chaco";
    final String WHEN = "2020-31-08";

    Temperature mockResponse = new Temperature.Builder().location(CITY).when(WHEN).create();
    when(localWeatherConnector.find(any(), any())).thenReturn(mockResponse);

    var response = localWeatherConnector.find(CITY, DateHelper.from(WHEN));

    assertNotNull(response);
    assertEquals(CITY, response.getCity());
    assertEquals(WHEN, response.getWhen());
    assertNotNull(response.getCelciusValue());
  }
}
