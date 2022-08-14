package it.n4v4.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import lombok.Builder;

@Component
public class BeerLoader implements CommandLineRunner{

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
					.upc(444444443243284283L)
					.monOnHand(4)
					.price(new BigDecimal(14))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Peroni")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(444444443243284283L)
					.monOnHand(24)
					.price(new BigDecimal(7))
					.build());
		}
	}

}
