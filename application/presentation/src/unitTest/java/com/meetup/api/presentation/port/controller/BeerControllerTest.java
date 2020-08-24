package com.meetup.api.presentation.port.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.meetup.api.business.beer.provider.BeerCalculatorUseCase;
import com.meetup.api.business.beer.provider.domain.BoxBeerToPurchase;
import com.meetup.api.domain.weather.Temperature;
import java.io.IOException;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BeerControllerTest {

  @Mock private BeerCalculatorUseCase beerCalculatorUseCase;

  private BeerController beerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    beerController = new BeerController(beerCalculatorUseCase);
  }

  @Test
  void beerCalculate() throws ParseException, InterruptedException, IOException {
    int assistants = 10;
    String where = "Corrientes";
    String When = "2020-09-21";

    Temperature temperature = new Temperature.Builder().celciusValue(18.9).when(When).create();

    BoxBeerToPurchase expected =
        BoxBeerToPurchase.BoxBeerToPurchaseBuilder.aBoxBeerToPurchase()
            .withPurchased(2L)
            .withTemperature(temperature)
            .build();

    // doReturn(ResponseEntity.ok(expected)).when(beerController).beerCalculate(assistants, where,
    // When);

    when(beerCalculatorUseCase.calculateBeersFor(assistants, where, When)).thenReturn(expected);

    var result = beerController.beerCalculate(assistants, where, When);

    assertNotNull(result);
    assertEquals(200, result.getStatusCode().value());
  }
}
