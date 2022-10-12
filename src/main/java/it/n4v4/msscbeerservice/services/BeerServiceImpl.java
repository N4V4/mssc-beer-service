package it.n4v4.msscbeerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import it.n4v4.msscbeerservice.web.controller.NotFoundException;
import it.n4v4.msscbeerservice.web.mappers.BeerMapper;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	@Override
	public BeerDto getBeerById(UUID id) {
		return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto){
		// TODO Auto-generated method stub
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setUpc(beerDto.getUpc());
		beer.setPrice(beerDto.getPrice());
		
		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

}
