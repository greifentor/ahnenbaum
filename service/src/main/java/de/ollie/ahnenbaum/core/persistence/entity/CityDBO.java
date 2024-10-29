package de.ollie.ahnenbaum.core.persistence.entity;

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
@Entity(name = "City")
@Generated
@Table(name = "CITY")
public class CityDBO {

	@Id
	private UUID id;

	@Column(name = "NAME")
	private String name;
}
