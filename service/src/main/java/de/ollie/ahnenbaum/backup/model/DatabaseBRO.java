package de.ollie.ahnenbaum.backup.model;

import java.util.List;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class DatabaseBRO {

	private List<PlaceBRO> places;
	private List<ProfessionBRO> professions;
}
