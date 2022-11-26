package it.n4v4.msscbeerservice.bootstrap;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner{
	
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

	
	private final BeerRepository beerRepository;


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(beerRepository.count() == 0) {
			loadBeerObjects();
		}		
	}

	private void loadBeerObjects() {
		// TODO Auto-generated method stub
		if(beerRepository.count() == 0) {
			Beer birraPrima = beerRepository.save(Beer.builder()
					.beerName("Nastro Azzurro")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.upc(BEER_1_UPC)
					.minOnHand(4)
					.price(new BigDecimal(14))
					.build());
			
			Beer birraSeconda =beerRepository.save(Beer.builder()
					.beerName("Peroni")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_2_UPC)
					.minOnHand(24)
					.price(new BigDecimal(7))
					.build());
			
			Beer birraTerza = beerRepository.save(Beer.builder()
					.beerName("Icnusa")
					.beerStyle("Ale")
					.quantityToBrew(400)
					.upc(BEER_3_UPC)
					.minOnHand(24)
					.price(new BigDecimal(7))
					.build());
			
			
			beerRepository.save(birraPrima);
			beerRepository.save(birraSeconda);
			beerRepository.save(birraTerza);
		}
	}

}
