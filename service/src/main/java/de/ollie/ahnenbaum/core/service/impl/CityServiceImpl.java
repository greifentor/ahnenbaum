package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import de.ollie.ahnenbaum.core.service.port.persistence.CityPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class CityServiceImpl implements CityService {

	private static final String MESSAGE_ID_CANNOT_BE_NULL = "id cannot be null!";
	private static final String MESSAGE_NAME_CANNOT_BE_NULL = "name cannot be null!";
	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final CityPersistencePort persistencePort;

	@Override
	public City changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(MESSAGE_ID_CANNOT_BE_NULL, City.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(MESSAGE_NAME_CANNOT_BE_NULL, City.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.changeName(id, name);
	}

	@Override
	public City create(String name) {
		ensure(name != null, new ParameterIsNullException(MESSAGE_NAME_CANNOT_BE_NULL, City.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.create(name);
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(MESSAGE_ID_CANNOT_BE_NULL, City.class.getSimpleName(), "id"));
		persistencePort.deleteById(id);
	}

	@Override
	public Optional<City> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(MESSAGE_ID_CANNOT_BE_NULL, City.class.getSimpleName(), "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<City> findAll() {
		return persistencePort.findAll();
	}
}
