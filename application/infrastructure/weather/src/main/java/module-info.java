module com.meetup.api.infratructure.weather {
  requires com.google.gson;
  requires java.net.http;
  requires com.meetup.api.business;
  requires com.meetup.api.domain;
  requires io.github.resilience4j.circuitbreaker;
  requires io.github.resilience4j.timelimiter;
  requires vavr;

  exports com.meetup.api.infrastructure.connector.business.service;
}
