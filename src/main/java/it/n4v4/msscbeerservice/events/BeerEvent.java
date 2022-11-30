package it.n4v4.msscbeerservice.events;

import java.io.Serializable;

import it.n4v4.msscbeerservice.web.module.BeerDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BeerEvent implements Serializable{

	private final Long serialVersionId = -58385934589385L;
	
	private final BeerDto beerDto;
}
