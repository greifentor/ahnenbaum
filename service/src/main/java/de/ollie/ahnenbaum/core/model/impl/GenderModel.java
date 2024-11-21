package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.Gender;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class GenderModel implements Gender {

	private UUID id;
	private String name;
}
