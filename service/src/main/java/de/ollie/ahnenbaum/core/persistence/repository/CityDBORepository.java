package de.ollie.ahnenbaum.core.persistence.repository;

import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDBORepository extends JpaRepository<CityDBO, UUID> {}