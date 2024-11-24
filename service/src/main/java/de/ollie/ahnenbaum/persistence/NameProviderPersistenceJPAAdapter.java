package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.NameProvider;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import de.ollie.ahnenbaum.persistence.factory.DBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.DBOMapper;
import de.ollie.ahnenbaum.persistence.repository.NameProviderRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NameProviderPersistenceJPAAdapter<M extends NameProvider, D extends NameProvider>
	implements NameProviderPersistencePort<M> {

	private final String modelClassName = Gender.class.getSimpleName();

	private final DBOFactory<D> factory;
	private final DBOMapper<M, D> mapper;
	private final NameProviderRepository<D> repository;

	@Override
	public M create(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(modelClassName, "name"));
		ensure(repository.findByName(name).isEmpty(), new UniqueConstraintViolationException(name, modelClassName, "name"));
		D dbo = factory.create(name);
		return mapper.toModel(repository.save(dbo));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		repository.deleteById(id);
	}

	@Override
	public List<M> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<M> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public Optional<M> findByName(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		return repository.findByName(name).map(mapper::toModel);
	}

	@Override
	public M update(M model) {
		ensure(model != null, new ParameterIsNullException(modelClassName, "gender"));
		ensure(isUnique(model), new UniqueConstraintViolationException(model.getName(), modelClassName, "name"));
		return mapper.toModel(repository.save(mapper.toDBO(model)));
	}

	private boolean isUnique(M model) {
		return repository.findByName(model.getName()).map(dbo -> dbo.getId().equals(model.getId())).orElse(true);
	}
}
