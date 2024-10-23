package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.Relation;
import de.ollie.ahnenbaum.core.model.RelationType;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class RelationModel implements Relation {

	private UUID id;
	private RelationType relationType;
	private PersonModel person;
	private PersonModel inRelationWith;
}
