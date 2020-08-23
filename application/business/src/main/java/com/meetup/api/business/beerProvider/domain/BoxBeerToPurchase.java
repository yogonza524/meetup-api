package com.meetup.api.business.beerProvider.domain;

import com.meetup.api.domain.weather.Temperature;
import java.io.Serializable;

public class BoxBeerToPurchase implements Serializable {
  private Long purchased;
  private Temperature temperature;

  public Long getPurchased() {
    return purchased;
  }

  public Temperature getTemperature() {
    return temperature;
  }

  public static final class BoxBeerToPurchaseBuilder {
    private Long purchased;
    private Temperature temperature;

    private BoxBeerToPurchaseBuilder() {}

    public static BoxBeerToPurchaseBuilder aBoxBeerToPurchase() {
      return new BoxBeerToPurchaseBuilder();
    }

    public BoxBeerToPurchaseBuilder withPurchased(Long purchased) {
      this.purchased = purchased;
      return this;
    }

    public BoxBeerToPurchaseBuilder withTemperature(Temperature temperature) {
      this.temperature = temperature;
      return this;
    }

    public BoxBeerToPurchase build() {
      BoxBeerToPurchase boxBeerToPurchase = new BoxBeerToPurchase();
      boxBeerToPurchase.purchased = this.purchased;
      boxBeerToPurchase.temperature = this.temperature;
      return boxBeerToPurchase;
    }
  }
}
