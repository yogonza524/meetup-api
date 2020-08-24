package com.meetup.api.presentation.port.controller;

import com.meetup.api.business.beer.provider.BeerCalculatorUseCase;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {
  private final BeerCalculatorUseCase beerCalculatorUseCase;

  public BeerController(BeerCalculatorUseCase beerCalculatorUseCase) {
    this.beerCalculatorUseCase = beerCalculatorUseCase;
  }

  @GetMapping(
      value = "/beer/{assistants}/{where}/{date}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity beerCalculate(
      @PathVariable int assistants, @PathVariable String where, @PathVariable String date)
      throws ParseException, IOException, InterruptedException {

    return ResponseEntity.ok(beerCalculatorUseCase.calculateBeersFor(assistants, where, date));
  }
}
