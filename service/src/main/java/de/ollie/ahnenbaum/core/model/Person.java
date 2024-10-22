package de.ollie.ahnenbaum.core.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class Person {

	private UUID uuid;
	private City bornIn;
	private LocalDateTime dateOfBirth;
	private LocalDateTime dateOfDeath;
	private City diedIn;
	private String forename;
	private Gender gender;
	private String lastname;
}
