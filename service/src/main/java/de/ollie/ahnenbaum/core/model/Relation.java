package de.ollie.ahnenbaum.core.model;

import java.util.UUID;

public interface Relation {
	UUID getId();

	RelationType getRelationType();

	Person getPerson();

	Person getInRelationWith();
}
