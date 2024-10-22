package de.ollie.ahnenbaum.core.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class PersonProfession {

	private UUID uuid;
	private LocalDateTime from;
	private Person person;
	private Profession profession;
	private LocalDateTime to;
}
