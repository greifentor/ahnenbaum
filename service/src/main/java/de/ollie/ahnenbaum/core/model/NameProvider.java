package de.ollie.ahnenbaum.core.model;

import java.util.UUID;

public interface NameProvider {
	UUID getId();

	String getName();
}
