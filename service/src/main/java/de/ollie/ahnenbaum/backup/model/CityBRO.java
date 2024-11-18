package de.ollie.ahnenbaum.backup.model;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CityBRO {

	private UUID id;
	private String name;
}
