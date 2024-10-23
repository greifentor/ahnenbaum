package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.City;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class CityModel implements City {

	private UUID id;
	private String name;
}
