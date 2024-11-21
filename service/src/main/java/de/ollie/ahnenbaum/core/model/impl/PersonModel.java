package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.Person;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class PersonModel implements Person {

	private UUID id;
	private PlaceModel bornIn;
	private LocalDateTime dateOfBirth;
	private LocalDateTime dateOfDeath;
	private PlaceModel diedIn;
	private String forename;
	private Gender gender;
	private String lastname;
}
