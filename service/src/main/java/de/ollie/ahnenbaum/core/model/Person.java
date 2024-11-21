package de.ollie.ahnenbaum.core.model;

import de.ollie.ahnenbaum.core.model.impl.PlaceModel;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Person {
	UUID getId();

	PlaceModel getBornIn();

	LocalDateTime getDateOfBirth();

	LocalDateTime getDateOfDeath();

	PlaceModel getDiedIn();

	String getForename();

	Gender getGender();

	String getLastname();
}
