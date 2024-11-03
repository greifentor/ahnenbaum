package de.ollie.ahnenbaum.persistence.repository;

import de.ollie.ahnenbaum.persistence.entity.CityDBO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDBORepository extends JpaRepository<CityDBO, UUID> {}
