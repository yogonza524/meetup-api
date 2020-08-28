module com.meetup.api.business {
  requires com.meetup.api.domain;

  exports com.meetup.api.business.weather;
  exports com.meetup.api.business.beer.provider.domain;
  exports com.meetup.api.business.beer.provider;
  exports com.meetup.api.business.weather.exceptions;
}
