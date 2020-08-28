package com.meetup.api.infrastructure.connector.business.service;

import com.google.gson.Gson;
import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.business.weather.exceptions.WeatherConnectionException;
import com.meetup.api.domain.weather.Temperature;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

public class LocalWeatherService implements WeatherService {
  private HttpClient httpClient;
  private final Gson gson = new Gson();
  private final String host;
  private final int port;
  private static final String ENDPOINT = "/weather";
  private static final String SLASH = "/";

  private TimeLimiter timeLimiter =
          TimeLimiter.of(
                  "weather-timelimiter",
                  TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build());

  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

  public LocalWeatherService(String host, int port) {
    this.host = host;
    this.port = port;
    this.httpClient = HttpClient.newBuilder().build();
  }

  public LocalWeatherService(HttpClient httpClient, String host, int port, TimeLimiter timeLimiter, ScheduledExecutorService scheduler) {
    this.httpClient = httpClient;
    this.host = host;
    this.port = port;
    this.timeLimiter = timeLimiter;
    this.scheduler = scheduler;
  }

  private boolean isNot2xxSuccessful(int statusCode) {
    return statusCode / 100 != 2;
  }

  @Override
  public Temperature find(String city, String when) throws WeatherConnectionException {
    CompletableFuture<Temperature> result =
        timeLimiter
            .executeCompletionStage(
                scheduler, () -> CompletableFuture.supplyAsync(this.findThroughHttp(city, when)))
            .toCompletableFuture();

    try {
      return result.get();
    } catch (InterruptedException e) {
      throw new WeatherConnectionException("Temperature service timeout", e);
    } catch (ExecutionException e) {
      throw new WeatherConnectionException("Temperature service broke", e);
    }
  }

  private Supplier<Temperature> findThroughHttp(String city, String when) {
    return () -> {
      final String uri =
          new StringBuilder()
              .append(host)
              .append(":")
              .append(port)
              .append(ENDPOINT)
              .append(SLASH)
              .append(city)
              .append(SLASH)
              .append(when)
              .toString();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

      HttpResponse<String> response = null;
      try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (IOException e) {
        throw new WeatherConnectionException("Temperature Service IO Error", e);
      } catch (InterruptedException e) {
        throw new WeatherConnectionException("Temperature Service Interruption Error", e);
      }

      if (isNot2xxSuccessful(response.statusCode())) {
        throw new WeatherConnectionException(
            "Temperature service code error: " + response.statusCode(), null);
      }
      return gson.fromJson(response.body(), Temperature.class);
    };
  }
}
