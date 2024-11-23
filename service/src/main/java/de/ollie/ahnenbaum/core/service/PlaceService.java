package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.Place;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceService {
	Place create(String name);

	void deleteById(UUID id);

	Optional<Place> findById(UUID id);

	List<Place> findAll();

	Place update(Place place);
}
