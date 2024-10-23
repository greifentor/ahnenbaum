package de.ollie.ahnenbaum.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PersonProfession {
	UUID getId();

	LocalDateTime getFrom();

	Person getPerson();

	Profession getProfession();

	LocalDateTime getTo();
}
