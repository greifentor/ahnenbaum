package de.ollie.ahnenbaum.persistence.repository;

import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDBORepository extends JpaRepository<GenderDBO, UUID> {
	Optional<GenderDBO> findByName(String name);
}
