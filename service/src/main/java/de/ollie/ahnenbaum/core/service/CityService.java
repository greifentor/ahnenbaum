package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.City;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
	City create(String name);

	void changeName(UUID id, String name);

	Optional<City> findById(UUID id);

	List<City> findAll();
}
