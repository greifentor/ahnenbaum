package de.ollie.ahnenbaum.core.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class City {

	private UUID uuid;
	private String name;
}
