package de.ollie.ahnenbaum.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Entity(name = "Person")
@Generated
@Table(name = "PERSON")
public class PersonDBO {

	@Id
	private UUID id;

	@Column(name = "BIRTH_NAME")
	private String birthName;

	@Column(name = "BORN_IN_PLACE")
	private CityDBO bornInPlace;

	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

	@Column(name = "DATE_OF_DEATH")
	private String dateOfDeath;

	@Column(name = "DIED_IN_PLACE")
	private CityDBO diedInPlace;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "GENDER", nullable = false)
	private GenderDBO gender;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
}
