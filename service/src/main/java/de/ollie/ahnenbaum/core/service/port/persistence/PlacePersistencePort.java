package de.ollie.ahnenbaum.core.service.port.persistence;

import de.ollie.ahnenbaum.core.model.Place;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlacePersistencePort {
	Place changeName(UUID id, String name);

	Place create(String name);

	void deleteById(UUID uuid);

	List<Place> findAll();

	Optional<Place> findById(UUID id);
}
