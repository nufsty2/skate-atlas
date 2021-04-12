package com.nufsty2.skateatlas.skatepark;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface SkateparkRepository extends CrudRepository<Skatepark, Integer> {

	Collection<Skatepark> findByCountry(String country);

	Collection<Skatepark> findByState(String state);

	Collection<Skatepark> findByCity(String city);

	Skatepark findById(int id);

}
