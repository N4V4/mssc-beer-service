package it.n4v4.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.n4v4.msscbeerservice.services.beer.BeerService;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import it.n4v4.msscbeerservice.web.module.BeerPagedList;
import it.n4v4.msscbeerservice.web.module.BeerStyleEnum;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerController {
	
	private final BeerService beerService;
	
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;


    @GetMapping(produces = { "application/json" }, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }
	
	@GetMapping({"/beer/{beerId}"})
	public ResponseEntity<BeerDto> getBeerById(@PathVariable ("beerId") UUID id, @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
		
		return new ResponseEntity<>(beerService.getBeerById(id,showInventoryOnHand), HttpStatus.OK);
	}
	
	@PostMapping({"/beer"})
	public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
		
		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/beer/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable ("beerId")UUID id, @Validated @RequestBody BeerDto beerDto) {
		
		return new ResponseEntity<>(beerService.updateBeer(id, beerDto), HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping({"/beerUpc/{upc}"})
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable ("upc") String upc, @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
		
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
		return new ResponseEntity<>(beerService.getBeerByUpc(upc,showInventoryOnHand), HttpStatus.OK);
	}

}
