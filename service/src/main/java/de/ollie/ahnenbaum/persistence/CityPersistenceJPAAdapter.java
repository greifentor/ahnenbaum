package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.port.persistence.CityPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.persistence.factory.CityDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.CityDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.CityDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CityPersistenceJPAAdapter implements CityPersistencePort {

	private static final String MESSAGE_ID_CANNOT_BE_NULL = "id cannot be null!";
	private static final String MESSAGE_NAME_CANNOT_BE_NULL = "name cannot be null!";
	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";
	private final CityDBOFactory factory;
	private final CityDBOMapper mapper;
	private final CityDBORepository repository;

	@Override
	public void changeName(UUID id, String name) {
		ensure(id != null, MESSAGE_ID_CANNOT_BE_NULL);
		ensure(name != null, MESSAGE_NAME_CANNOT_BE_NULL);
		ensure(!name.isBlank(), MESSAGE_NAME_CANNOT_BE_EMPTY);
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
		ensure(name != null, MESSAGE_NAME_CANNOT_BE_NULL);
		ensure(!name.isBlank(), MESSAGE_NAME_CANNOT_BE_EMPTY);
		ensure(repository.findByName(name).isEmpty(), new UniqueConstraintViolationException("City", "name"));
		CityDBO city = factory.create(name);
		return mapper.toModel(repository.save(city));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, MESSAGE_ID_CANNOT_BE_NULL);
		repository.deleteById(id);
	}

	@Override
	public List<City> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<City> findById(UUID id) {
		ensure(id != null, MESSAGE_ID_CANNOT_BE_NULL);
		return repository.findById(id).map(mapper::toModel);
	}
}
