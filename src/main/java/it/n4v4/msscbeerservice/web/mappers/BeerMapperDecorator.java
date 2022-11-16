package it.n4v4.msscbeerservice.web.mappers;


import org.springframework.beans.factory.annotation.Autowired;

import it.n4v4.msscbeerservice.domain.Beer;
import it.n4v4.msscbeerservice.services.inventory.BeerInventoryService;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jt on 2019-06-08.
 */
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
       return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);//provo ad impotare a mano un valore
        dto.setQuantityOnHand(45678);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}