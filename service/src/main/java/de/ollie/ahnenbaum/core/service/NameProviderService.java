package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.NameProvider;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NameProviderService<M extends NameProvider> {
	M create(String name);

	void deleteById(UUID id);

	Optional<M> findById(UUID id);

	List<M> findAll();

	M update(M model);
}
