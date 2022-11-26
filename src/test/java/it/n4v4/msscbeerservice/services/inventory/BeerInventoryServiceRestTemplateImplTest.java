package it.n4v4.msscbeerservice.services.inventory;


import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.n4v4.msscbeerservice.bootstrap.BeerLoader;

 // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {

        //todo evolve to use UPC
       //Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_UUID_1);

        //System.out.println("Stampiamo risultato "+qoh);
        

    }
}
