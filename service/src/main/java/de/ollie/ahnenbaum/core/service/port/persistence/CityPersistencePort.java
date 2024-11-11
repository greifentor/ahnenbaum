package de.ollie.ahnenbaum.core.service.port.persistence;

import de.ollie.ahnenbaum.core.model.City;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityPersistencePort {
	City changeName(UUID id, String name);

	City create(String name);

	void deleteById(UUID uuid);

	List<City> findAll();

	Optional<City> findById(UUID id);
}
