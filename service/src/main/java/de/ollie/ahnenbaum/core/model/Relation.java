package de.ollie.ahnenbaum.core.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class Relation {

	private UUID uuid;
	private RelationType relationType;
	private Person person;
	private Person inRelationWith;
}
