package de.ollie.ahnenbaum.core.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.model.impl.CityModel;
import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.core.service.port.CityPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CityPersistenceJPAAdapter implements CityPersistencePort {

	private final UUIDService uuidService;

	@Override
	public City create(String name) {
		ensure(name != null, "name cannot be null!");
		ensure(!name.isBlank(), "name cannot be blank!");
		return new CityModel().setId(uuidService.create()).setName(name);
	}

	@Override
	public void changeName(UUID id, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<City> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
