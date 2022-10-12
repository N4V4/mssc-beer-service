package it.n4v4.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.web.module.BeerDto;


@Mapper(uses = {DataMapper.class},componentModel = "spring")
public interface BeerMapper {
	
	Beer beerDtoToBeer(BeerDto beerDto);
	
	BeerDto beerToBeerDto(Beer beer);

}
