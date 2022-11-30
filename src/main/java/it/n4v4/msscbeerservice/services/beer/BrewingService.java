package it.n4v4.msscbeerservice.services.beer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import it.n4v4.msscbeerservice.config.JmsConfig;
import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.events.BrewBeerEvent;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import it.n4v4.msscbeerservice.services.inventory.BeerInventoryService;
import it.n4v4.msscbeerservice.web.mappers.BeerMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewingService {
	
	private final BeerRepository beerRepository;
	private final BeerInventoryService beerInventoryService;
	private final BeerMapper beerMapper;
	private final JmsTemplate jmsTemplate;
	
	@Scheduled(fixedRate = 5000)
	public void checkLowInventory() {
		
		Iterable<Beer> listBeer = beerRepository.findAll();
		
		listBeer.forEach(beer -> {
			Integer inInventario = beerInventoryService.getOnhandInventory(beer.getId());
			
			log.debug("In inventario: "+ inInventario);
			log.debug("MinOnHand: " + beer.getMinOnHand());
			
			if(beer.getMinOnHand() >= inInventario) {
				jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
			}
			
		});
		
	}

}
