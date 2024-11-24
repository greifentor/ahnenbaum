package de.ollie.ahnenbaum.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NameProviderRepository<D> extends JpaRepository<D, UUID> {
	Optional<D> findByName(String name);
}
