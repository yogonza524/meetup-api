module com.meetup.api.infratructure.weather {
  requires com.google.gson;
  requires java.net.http;
  requires com.meetup.api.business;
  requires com.meetup.api.domain;

  exports com.meetup.api.infrastructure.connector.port;
  exports com.meetup.api.infrastructure.connector.business;
}
