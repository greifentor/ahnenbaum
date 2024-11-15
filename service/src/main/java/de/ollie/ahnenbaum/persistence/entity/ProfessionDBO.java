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
@Entity(name = "Profession")
@Generated
@Table(name = "PROFESSION")
public class ProfessionDBO {

	@Id
	private UUID id;

	@Column(name = "NAME", unique = true)
	private String name;
}
