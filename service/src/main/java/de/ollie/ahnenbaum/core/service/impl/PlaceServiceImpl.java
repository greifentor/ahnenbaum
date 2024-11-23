package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
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

	private final String modelClassName = Place.class.getSimpleName();

	@Override
	public Place create(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		ensure(
			persistencePort.findByName(name).isEmpty(),
			new UniqueConstraintViolationException(name, modelClassName, "name")
		);
		return persistencePort.create(name);
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		persistencePort.deleteById(id);
	}

	@Override
	public Optional<Place> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<Place> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Place update(Place place) {
		ensure(place != null, new ParameterIsNullException(modelClassName, "place"));
		return persistencePort.update(place);
	}
}
