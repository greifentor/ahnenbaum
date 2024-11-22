package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import de.ollie.ahnenbaum.persistence.factory.GenderDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.GenderDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.GenderDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class GenderPersistenceJPAAdapter implements GenderPersistencePort {

	private final GenderDBOFactory factory;
	private final GenderDBOMapper mapper;
	private final GenderDBORepository repository;

	@Override
	public Gender create(String name) {
		ensure(name != null, new ParameterIsNullException(Place.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(Place.class.getSimpleName(), "name"));
		ensure(
			repository.findByName(name).isEmpty(),
			new UniqueConstraintViolationException(name, Place.class.getSimpleName(), "name")
		);
		GenderDBO dbo = factory.create(name);
		return mapper.toModel(repository.save(dbo));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Place.class.getSimpleName(), "id"));
		repository.deleteById(id);
	}

	@Override
	public List<Gender> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<Gender> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Gender.class.getSimpleName(), "id"));
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public Optional<Gender> findByName(String name) {
		ensure(name != null, new ParameterIsNullException(Gender.class.getSimpleName(), "name"));
		return repository.findByName(name).map(mapper::toModel);
	}

	@Override
	public Gender update(Gender gender) {
		ensure(gender != null, new ParameterIsNullException(Gender.class.getSimpleName(), "gender"));
		ensure(
			isUnique(gender),
			new UniqueConstraintViolationException(gender.getName(), Place.class.getSimpleName(), "name")
		);
		return mapper.toModel(repository.save(mapper.toDBO(gender)));
	}

	private boolean isUnique(Gender gender) {
		return repository.findByName(gender.getName()).map(dbo -> dbo.getId().equals(gender.getId())).orElse(true);
	}
}
