package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
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

	@Override
	public Place changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(Place.class.getSimpleName(), "name"));
		return repository
			.findById(id)
			.map(dbo -> setName(dbo, name))
			.orElseThrow(() -> new NoSuchRecordException(id.toString(), Place.class.getSimpleName(), "name"));
	}

	private Place setName(PlaceDBO dbo, String name) {
		return mapper.toModel(repository.save(dbo.setName(name)));
	}

	@Override
	public Place create(String name) {
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(Place.class.getSimpleName(), "name"));
		ensure(
			repository.findByName(name).isEmpty(),
			new UniqueConstraintViolationException(name, Place.class.getSimpleName(), "name")
		);
		PlaceDBO place = factory.create(name);
		return mapper.toModel(repository.save(place));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		repository.deleteById(id);
	}

	@Override
	public List<Place> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<Place> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		return repository.findById(id).map(mapper::toModel);
	}
}
