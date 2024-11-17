package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.Profession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionService {
	Profession create(String name);

	Profession changeName(UUID id, String name);

	void deleteById(UUID id);

	Optional<Profession> findById(UUID id);

	List<Profession> findAll();
}
