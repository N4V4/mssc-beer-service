package it.n4v4.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.n4v4.msscbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

	//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
}
