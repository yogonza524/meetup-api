module com.meetup.api.business {
  requires com.meetup.api.domain;

  exports com.meetup.api.business.beerProvider;
  exports com.meetup.api.business.weather;
  exports com.meetup.api.business.beerProvider.domain;
}
