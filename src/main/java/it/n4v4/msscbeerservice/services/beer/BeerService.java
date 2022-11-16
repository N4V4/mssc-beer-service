package it.n4v4.msscbeerservice.services.beer;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import it.n4v4.msscbeerservice.web.module.BeerDto;
import it.n4v4.msscbeerservice.web.module.BeerPagedList;
import it.n4v4.msscbeerservice.web.module.BeerStyleEnum;

public interface BeerService {


	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
	
	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

	BeerDto getBeerById(UUID id, Boolean showInventoryOnHand);
	
	BeerDto getBeerByUpc(String up,Boolean showInventoryOnHandc);

}
