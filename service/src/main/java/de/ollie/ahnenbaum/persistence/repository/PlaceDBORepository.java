package de.ollie.ahnenbaum.persistence.repository;

import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceDBORepository extends JpaRepository<PlaceDBO, UUID> {
	Optional<PlaceDBO> findByName(String name);
}
