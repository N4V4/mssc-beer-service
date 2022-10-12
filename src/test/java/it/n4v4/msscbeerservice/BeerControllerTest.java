package it.n4v4.msscbeerservice;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import it.n4v4.msscbeerservice.bootstrap.BeerLoader;
import it.n4v4.msscbeerservice.services.BeerService;
import it.n4v4.msscbeerservice.web.controller.BeerController;
import it.n4v4.msscbeerservice.web.module.BeerDto;
import it.n4v4.msscbeerservice.web.module.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	
	@Test
	public void getBeerById() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/"+UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void saveNewBeer() throws Exception {
		
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void updateBeerById() throws Exception {
		
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"+UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson)).andExpect(MockMvcResultMatchers.status().isNoContent());
		
		
	}
	
	BeerDto getValidBeerDto() {
		return BeerDto.builder().beerName("Nastro Azzurro")
				.beerStyle(BeerStyleEnum.BIONDA)
				.price(new BigDecimal(23))
				.quantityOnHand(2)
				.upc(BeerLoader.BEER_NUM_2)
				.build();
				
		
	}
}
