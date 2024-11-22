package de.ollie.ahnenbaum.core.service.port.persistence;

import de.ollie.ahnenbaum.core.model.Gender;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {
	Gender changeName(UUID id, String name);

	Gender create(String firstName, String lastName, LocalDateTime dateOfBirth);

	void deleteById(UUID uuid);

	List<Gender> findAll();

	Optional<Gender> findById(UUID id);
}
