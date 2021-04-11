package com.nufsty2.skateatlas.skatepark;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SkateparkRepository extends CrudRepository<Skatepark, Integer> {

	List<Skatepark> findByCountry(String country);

	List<Skatepark> findByState(String state);

	List<Skatepark> findByCity(String city);

}
