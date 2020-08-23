package com.meetup.api.domain.beer.provider;

public enum BeerBoxSelled {
  SIX_PER_PURCHASE(6);

  private final int value;

  BeerBoxSelled(final int unitsPerBox) {
    this.value = unitsPerBox;
  }

  public int value() {
    return this.value;
  }
}
