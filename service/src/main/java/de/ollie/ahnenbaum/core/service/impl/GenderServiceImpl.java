package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class GenderServiceImpl implements GenderService {

	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final GenderPersistencePort persistencePort;

	@Override
	public Gender changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.changeName(id, name);
	}

	@Override
	public Gender create(String name) {
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
	public Optional<Gender> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<Gender> findAll() {
		return persistencePort.findAll();
	}
}
