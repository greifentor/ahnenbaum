package de.ollie.ahnenbaum.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Person {
	UUID getId();

	Place getBornIn();

	LocalDateTime getDateOfBirth();

	LocalDateTime getDateOfDeath();

	Place getDiedIn();

	String getForename();

	Gender getGender();

	String getLastname();
}
