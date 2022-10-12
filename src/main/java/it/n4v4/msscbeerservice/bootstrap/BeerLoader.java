package it.n4v4.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import lombok.Builder;

@Component
public class BeerLoader implements CommandLineRunner{
	
	public static final String BEER_NUM_1 = "afkafmk";
	public static final String BEER_NUM_2 = "cakcak";
	public static final String BEER_NUM_3 = "macòla";

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
					.monOnHand(4)
					.price(new BigDecimal(14))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Peroni")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_NUM_2)
					.monOnHand(24)
					.price(new BigDecimal(7))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Icnusa")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_NUM_3)
					.monOnHand(24)
					.price(new BigDecimal(7))
					.build());
		}
	}

}
