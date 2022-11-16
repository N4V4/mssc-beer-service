package it.n4v4.msscbeerservice.services.beer;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.repositories.BeerRepository;
import it.n4v4.msscbeerservice.web.controller.NotFoundException;
import it.n4v4.msscbeerservice.web.mappers.BeerMapper;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import it.n4v4.msscbeerservice.web.module.BeerPagedList;
import it.n4v4.msscbeerservice.web.module.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerCache", key = "#p0", condition = "# showInventoryOnHand == false ")
	@Override
	public BeerDto getBeerById(UUID id,Boolean showInventoryOnHand) {
		System.out.println("Chiamata per singola");
		if(showInventoryOnHand) {
			return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(id).orElseThrow(NotFoundException::new));
		}else {
			return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
		}
		
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO Auto-generated method stub
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setUpc(beerDto.getUpc());
		beer.setPrice(beerDto.getPrice());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Cacheable(cacheNames = "beerListCache",condition = "# showInventoryOnHand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {

		System.out.println("Chiamata per multipla");
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;
		log.info("Aiuto in cerca di informazioni");

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			// search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if (showInventoryOnHand) {
			log.info("Con inventario " + beerPage.getContent().get(0));
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory)
							.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			log.info("Nessun inventario");
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}
		log.info(beerPagedList.toString());

		return beerPagedList;
	}

	@Cacheable(cacheNames = "beerUpcCache",condition = "# showInventoryOnHand == false ")
	@Override
	public BeerDto getBeerByUpc(String upc,Boolean showInventoryOnHand) {
		// TODO Auto-generated method stub
		
		if(showInventoryOnHand) {
			System.out.println("Chiamata per singola con inventario");
			return beerMapper.beerToBeerDtoWithInventory(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
		}else {
			System.out.println("Chiamata per singola senza inventario");
			return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
		}

	}

}
