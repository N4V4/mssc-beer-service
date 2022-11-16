package it.n4v4.msscbeerservice.bootstrap;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import lombok.Builder;

//@Component
public class BeerLoader implements CommandLineRunner{
	
	public static final String BEER_NUM_1 = "afkafmk";
	public static final String BEER_NUM_2 = "cakcak";
	public static final String BEER_NUM_3 = "macòla";
	public static final UUID BEER_UUID_1 = UUID.fromString("30613831-3839-3333-2d30-3837642d3437");
	public static final UUID BEER_UUID_2 = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
	public static final UUID BEER_UUID_3 = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
	
	private final BeerRepository beerRepository;
	
	
	public BeerLoader(BeerRepository beerRepository) {
		super();
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		// TODO Auto-generated method stub
		if(beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Nastro Azzurro")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.upc(BEER_NUM_1)
					.minOnHand(4)
					.price(new BigDecimal(14))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Peroni")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_NUM_2)
					.minOnHand(24)
					.price(new BigDecimal(7))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Icnusa")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_NUM_3)
					.minOnHand(24)
					.price(new BigDecimal(7))
					.build());
		}
	}

}
