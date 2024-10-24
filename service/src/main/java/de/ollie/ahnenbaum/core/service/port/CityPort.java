package de.ollie.ahnenbaum.core.service.port;

import de.ollie.ahnenbaum.core.model.City;
import java.util.List;
import java.util.UUID;

public interface CityPort {
	City create(String name);

	void changeName(String name);

	City findById(UUID id);

	List<City> findAll();
}
