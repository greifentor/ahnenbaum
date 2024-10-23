package de.ollie.ahnenbaum.core.model;

import de.ollie.ahnenbaum.core.model.impl.CityModel;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Person {
	UUID getId();

	CityModel getBornIn();

	LocalDateTime getDateOfBirth();

	LocalDateTime getDateOfDeath();

	CityModel getDiedIn();

	String getForename();

	Gender getGender();

	String getLastname();
}
