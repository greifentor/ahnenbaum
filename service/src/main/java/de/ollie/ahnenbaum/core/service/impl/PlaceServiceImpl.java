package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class PlaceServiceImpl implements PlaceService {

	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final PlacePersistencePort persistencePort;

	@Override
	public Place changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.changeName(id, name);
	}

	@Override
	public Place create(String name) {
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.create(name);
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		persistencePort.deleteById(id);
	}

	@Override
	public Optional<Place> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<Place> findAll() {
		return persistencePort.findAll();
	}
}
