package it.n4v4.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import it.n4v4.msscbeerservice.web.module.BeerStyleEnum;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

	BeerDto save(BeerDto beerDto);

	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

	Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, PageRequest pageRequest);

	//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
}
