package it.n4v4.msscbeerservice.services.inventory;


import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
public interface BeerInventoryService {

    Integer getOnhandInventory(UUID beerId);
}
