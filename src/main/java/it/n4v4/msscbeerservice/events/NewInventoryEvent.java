package it.n4v4.msscbeerservice.events;

import it.n4v4.msscbeerservice.web.module.BeerDto;

public class NewInventoryEvent extends BeerEvent{

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
		// TODO Auto-generated constructor stub
	}

}
