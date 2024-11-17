package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class ProfessionServiceImpl implements ProfessionService {

	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";

	private final ProfessionPersistencePort persistencePort;

	@Override
	public Profession changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(Profession.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.changeName(id, name);
	}

	@Override
	public Profession create(String name) {
		ensure(name != null, new ParameterIsNullException(Profession.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ServiceException(MESSAGE_NAME_CANNOT_BE_EMPTY, null, ""));
		return persistencePort.create(name);
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		persistencePort.deleteById(id);
	}

	@Override
	public Optional<Profession> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		return persistencePort.findById(id);
	}

	@Override
	public List<Profession> findAll() {
		return persistencePort.findAll();
	}
}
