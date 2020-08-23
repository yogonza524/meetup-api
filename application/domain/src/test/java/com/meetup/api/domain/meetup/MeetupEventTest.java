package com.meetup.api.domain.meetup;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;

class MeetupEventTest {
  private final String COUNTRY = "ARGENTINA";
  private final String CITY = "ARGENTINA";
  private final String ADDRESS = "ARGENTINA";
  private final Long BEERS = 12L;

  @Test
  public void shouldPassWhenMeetUpIsCreated() {
    MeetupEvent meetupEvent =
        new MeetupEvent.Builder()
            .when(new Date())
            .where(new Place.Builder().country(COUNTRY).city(CITY).adddress(ADDRESS).create())
            .withBeers(BEERS)
            .create();

    assertNotNull(meetupEvent);
    assertEquals(COUNTRY, meetupEvent.getWhere().getCountry());
    assertEquals(CITY, meetupEvent.getWhere().getCity());
    assertEquals(ADDRESS, meetupEvent.getWhere().getAddress());
    assertEquals(BEERS, meetupEvent.getTotalOfBeers());
  }
}
