package com.meetup.api.presentation.business.config;

import com.meetup.api.business.beer.provider.BeerCalculatorUseCase;
import com.meetup.api.business.beer.provider.BeerProviderService;
import com.meetup.api.business.beer.provider.SimpleBeerProviderService;
import com.meetup.api.business.weather.WeatherService;
import com.meetup.api.infrastructure.connector.port.LocalWeatherConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
  @Value("${weather-service.host}")
  private String weatherHost;

  @Value("${weather-service.port}")
  private int weatherPort;

  @Bean
  BeerProviderService beerProviderService() {
    return new SimpleBeerProviderService();
  }

  @Bean
  WeatherService weatherService() {
    return new LocalWeatherConnector(weatherHost, weatherPort);
  }

  @Bean
  BeerCalculatorUseCase beerCalculatorUseCase() {
    return new BeerCalculatorUseCase(beerProviderService(), weatherService());
  }
}
