package de.ollie.ahnenbaum.backup.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DatabaseBRO {

	private List<CityBRO> cities;
	private List<ProfessionBRO> professions;
}
