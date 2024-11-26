package de.ollie.ahnenbaum.persistence.factory;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.PersonDBO;
import jakarta.inject.Named;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class PersonDBOFactory {

	private final UUIDService uuidService;

	public PersonDBO create(String firstName, String lastName, LocalDateTime dateOfBirth) {
		ensure(firstName != null, "first name cannot be null!");
		return new PersonDBO()
			.setDateOfBirth(dateOfBirth)
			.setFirstName(firstName)
			.setId(uuidService.create())
			.setLastName(lastName);
	}
}
