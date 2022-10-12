package it.n4v4.msscbeerservice.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import it.n4v4.msscbeerservice.web.module.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID id);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

}
