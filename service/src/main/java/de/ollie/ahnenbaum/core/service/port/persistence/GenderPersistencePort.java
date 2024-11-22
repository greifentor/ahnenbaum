package de.ollie.ahnenbaum.core.service.port.persistence;

import de.ollie.ahnenbaum.core.model.Gender;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenderPersistencePort {
	Gender create(String name);

	void deleteById(UUID uuid);

	List<Gender> findAll();

	Optional<Gender> findById(UUID id);

	Optional<Gender> findByName(String name);

	Gender update(Gender gender);
}
