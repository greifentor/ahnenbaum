package de.ollie.ahnenbaum.persistence.repository;

import de.ollie.ahnenbaum.persistence.entity.PersonDBO;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDBORepository extends JpaRepository<PersonDBO, UUID> {
	List<PersonDBO> findByName(String name);
}
