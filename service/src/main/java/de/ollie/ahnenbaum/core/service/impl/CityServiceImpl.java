package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import de.ollie.ahnenbaum.core.service.port.CityPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class CityServiceImpl implements CityService {

	private final CityPersistencePort persistencePort;

	@Override
	public void changeName(UUID id, String name) {
		ensure(id != null, new ServiceException("id cannot be null", null, ""));
		ensure(name != null, new ServiceException("name cannot be null", null, ""));
		ensure(!name.isEmpty(), new ServiceException("name cannot be empty", null, ""));
		persistencePort
			.findById(id)
			.ifPresentOrElse(
				city -> persistencePort.changeName(id, name),
				() -> {
					throw new NoSuchElementException("there is no city with id: " + id);
				}
			);
	}

	@Override
	public City create(String name) {
		ensure(name != null, new ServiceException("name cannot be null", null, ""));
		ensure(!name.isEmpty(), new ServiceException("name cannot be empty", null, ""));
		return persistencePort.create(name);
	}

	@Override
	public Optional<City> findById(UUID id) {
		ensure(id != null, new ServiceException("id cannot be null", null, ""));
		return persistencePort.findById(id);
	}

	@Override
	public List<City> findAll() {
		return persistencePort.findAll();
	}
}
