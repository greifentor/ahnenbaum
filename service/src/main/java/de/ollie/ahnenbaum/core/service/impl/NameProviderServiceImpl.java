package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.NameProvider;
import de.ollie.ahnenbaum.core.service.NameProviderService;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class NameProviderServiceImpl<M extends NameProvider> implements NameProviderService<M> {

	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final NameProviderPersistencePort<M> persistencePort;

	private final String modelClassName = Gender.class.getSimpleName();

	@Override
	public M create(String name) {
		ensure(name != null, new ParameterIsNullException(modelClassName, "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		ensure(
			persistencePort.findByName(name).isEmpty(),
			new UniqueConstraintViolationException(name, modelClassName, "name")
		);
		return persistencePort.create(name);
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		persistencePort.deleteById(id);
	}

	@Override
	public Optional<M> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<M> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public M update(M model) {
		ensure(model != null, new ParameterIsNullException(modelClassName, "model"));
		return persistencePort.update(model);
	}
}
