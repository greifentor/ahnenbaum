package de.ollie.ahnenbaum.core.service.port.persistence;

import de.ollie.ahnenbaum.core.model.Profession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionPersistencePort {
	Profession changeName(UUID id, String name);

	Profession create(String name);

	void deleteById(UUID uuid);

	List<Profession> findAll();

	Optional<Profession> findById(UUID id);
}
