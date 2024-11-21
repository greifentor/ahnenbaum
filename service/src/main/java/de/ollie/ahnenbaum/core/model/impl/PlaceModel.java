package de.ollie.ahnenbaum.core.model.impl;

import de.ollie.ahnenbaum.core.model.Place;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class PlaceModel implements Place {

	private UUID id;
	private String name;
}
