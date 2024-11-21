package de.ollie.ahnenbaum.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@JoinColumn(name = "BORN_IN_PLACE", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private PlaceDBO bornInPlace;

	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

	@Column(name = "DATE_OF_DEATH")
	private String dateOfDeath;

	@JoinColumn(name = "DIED_IN_PLACE", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private PlaceDBO diedInPlace;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@JoinColumn(name = "GENDER", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private GenderDBO gender;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
}
