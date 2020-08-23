package com.meetup.api.infrastructure.connector.port;

import com.google.gson.Gson;
import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.domain.weather.Temperature;
import com.meetup.api.infrastructure.connector.port.excecptions.ErrorConnectionException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocalWeatherConnector implements WeatherService {
  private final HttpClient httpClient = HttpClient.newBuilder().build();
  private final Gson gson = new Gson();
  private final String host;
  private final int port;
  private final String endpoint = "/weather";

  private final String SLASH = "/";

  public LocalWeatherConnector(String host, int port) {
    this.host = host;
    this.port = port;
  }

  private boolean isNot2xxSuccessful(int statusCode) {
    return statusCode / 100 != 2;
  }

  @Override
  public Temperature find(String city, String when) throws IOException, InterruptedException {
    final String uri =
        new StringBuilder()
            .append(host)
            .append(":")
            .append(port)
            .append(endpoint)
            .append(SLASH)
            .append(city)
            .append(SLASH)
            .append(when)
            .toString();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
    var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    if (isNot2xxSuccessful(response.statusCode())) {
      throw new ErrorConnectionException("Weather API response with code " + response.statusCode());
    }
    return gson.fromJson(response.body(), Temperature.class);
  }
}
