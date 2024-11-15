package de.ollie.ahnenbaum.persistence.repository;

import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionDBORepository extends JpaRepository<ProfessionDBO, UUID> {
	Optional<ProfessionDBO> findByName(String name);
}
