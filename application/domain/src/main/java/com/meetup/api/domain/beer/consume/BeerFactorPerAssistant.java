package com.meetup.api.domain.beer.consume;

public enum BeerFactorPerAssistant {
  ONE(1.0),
  TWO(2.0),
  THREE_QUARTERS(0.75);

  private final double factor;

  BeerFactorPerAssistant(double factor) {
    this.factor = factor;
  }

  public double value() {
    return factor;
  }
}
