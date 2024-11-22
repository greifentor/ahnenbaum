package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.Gender;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenderService {
	Gender create(String name);

	void deleteById(UUID id);

	Optional<Gender> findById(UUID id);

	List<Gender> findAll();

	Gender update(Gender gender);
}
