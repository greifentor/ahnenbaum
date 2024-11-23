package de.ollie.ahnenbaum.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Entity(name = "Place")
@Generated
@Table(name = "PLACE")
public class PlaceDBO {

	@Id
	private UUID id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "VERSION")
	@Version
	private Integer version;
}
