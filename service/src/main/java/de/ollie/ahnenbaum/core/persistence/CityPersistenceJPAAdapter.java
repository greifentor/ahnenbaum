package de.ollie.ahnenbaum.core.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.core.persistence.factory.CityDBOFactory;
import de.ollie.ahnenbaum.core.persistence.mapper.CityDBOMapper;
import de.ollie.ahnenbaum.core.persistence.repository.CityDBORepository;
import de.ollie.ahnenbaum.core.service.port.CityPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CityPersistenceJPAAdapter implements CityPersistencePort {

	private final CityDBOFactory factory;
	private final CityDBOMapper mapper;
	private final CityDBORepository repository;

	@Override
	public void changeName(UUID id, String name) {
		ensure(id != null, "id cannot be null!");
		ensure(name != null, "name cannot be null!");
		ensure(!name.isBlank(), "name cannot be blank!");
		repository
			.findById(id)
			.ifPresentOrElse(
				dbo -> {
					dbo.setName(name);
					repository.save(dbo);
				},
				() -> {
					throw new NoSuchElementException("there is no city with id: " + id);
				}
			);
	}

	@Override
	public City create(String name) {
		ensure(name != null, "name cannot be null!");
		ensure(!name.isBlank(), "name cannot be blank!");
		CityDBO city = factory.create(name);
		return mapper.toModel(repository.save(city));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, "id cannot be null!");
		repository.deleteById(id);
	}

	@Override
	public List<City> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<City> findById(UUID id) {
		ensure(id != null, "id cannot be null!");
		return repository.findById(id).map(dbo -> mapper.toModel(dbo));
	}
}
