package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.PersonProfession;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class PersonProfessionModel implements PersonProfession {

	private UUID id;
	private LocalDateTime from;
	private PersonModel person;
	private ProfessionModel profession;
	private LocalDateTime to;
}
