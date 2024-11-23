package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import de.ollie.ahnenbaum.persistence.factory.PlaceDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.PlaceDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.PlaceDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class PlacePersistenceJPAAdapter implements PlacePersistencePort {

	private final PlaceDBOFactory factory;
	private final PlaceDBOMapper mapper;
	private final PlaceDBORepository repository;

	private final String modelClassName = Place.class.getSimpleName();

	@Override
	public Place create(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(modelClassName, "name"));
		ensure(repository.findByName(name).isEmpty(), new UniqueConstraintViolationException(name, modelClassName, "name"));
		PlaceDBO dbo = factory.create(name);
		return mapper.toModel(repository.save(dbo));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		repository.deleteById(id);
	}

	@Override
	public List<Place> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<Place> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public Optional<Place> findByName(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		return repository.findByName(name).map(mapper::toModel);
	}

	@Override
	public Place update(Place place) {
		ensure(place != null, new ParameterIsNullException(modelClassName, "place"));
		ensure(isUnique(place), new UniqueConstraintViolationException(place.getName(), modelClassName, "name"));
		return mapper.toModel(repository.save(mapper.toDBO(place)));
	}

	private boolean isUnique(Place place) {
		return repository.findByName(place.getName()).map(dbo -> dbo.getId().equals(place.getId())).orElse(true);
	}
}
