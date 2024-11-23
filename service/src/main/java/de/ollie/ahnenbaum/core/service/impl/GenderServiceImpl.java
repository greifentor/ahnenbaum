package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class GenderServiceImpl implements GenderService {

	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final GenderPersistencePort persistencePort;

	private final String modelClassName = Gender.class.getSimpleName();

	@Override
	public Gender create(String name) {
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
	public Optional<Gender> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(modelClassName, "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<Gender> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Gender update(Gender gender) {
		ensure(gender != null, new ParameterIsNullException(modelClassName, "gender"));
		return persistencePort.update(gender);
	}
}
