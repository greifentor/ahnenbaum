package de.ollie.ahnenbaum.core.service.port.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NameProviderPersistencePort<M> {
	M create(String name);

	void deleteById(UUID uuid);

	List<M> findAll();

	Optional<M> findById(UUID id);

	Optional<M> findByName(String name);

	M update(M model);
}
