package de.ollie.ahnenbaum.persistence;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import de.ollie.ahnenbaum.persistence.factory.ProfessionDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.ProfessionDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.ProfessionDBORepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ProfessionPersistenceJPAAdapter implements ProfessionPersistencePort {

	private static final String MESSAGE_NAME_CANNOT_BE_NULL = "name cannot be null!";
	private static final String MESSAGE_NAME_CANNOT_BE_EMPTY = "name cannot be blank!";
	private final ProfessionDBOFactory factory;
	private final ProfessionDBOMapper mapper;
	private final ProfessionDBORepository repository;

	@Override
	public Profession changeName(UUID id, String name) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		ensure(name != null, new ParameterIsNullException(Profession.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(Profession.class.getSimpleName(), "name"));
		return repository
			.findById(id)
			.map(dbo -> setName(dbo, name))
			.orElseThrow(() -> new NoSuchRecordException(id.toString(), Profession.class.getSimpleName(), "name"));
	}

	private Profession setName(ProfessionDBO dbo, String name) {
		return mapper.toModel(repository.save(dbo.setName(name)));
	}

	@Override
	public Profession create(String name) {
		ensure(name != null, new ParameterIsNullException(Profession.class.getSimpleName(), "name"));
		ensure(!name.isBlank(), new ParameterIsBlankException(Profession.class.getSimpleName(), "name"));
		ensure(
			repository.findByName(name).isEmpty(),
			new UniqueConstraintViolationException(name, Profession.class.getSimpleName(), "name")
		);
		ProfessionDBO Profession = factory.create(name);
		return mapper.toModel(repository.save(Profession));
	}

	@Override
	public void deleteById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		repository.deleteById(id);
	}

	@Override
	public List<Profession> findAll() {
		return repository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<Profession> findById(UUID id) {
		ensure(id != null, new ParameterIsNullException(Profession.class.getSimpleName(), "id"));
		return repository.findById(id).map(mapper::toModel);
	}
}
