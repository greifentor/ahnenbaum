package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.model.impl.CityModel;
import de.ollie.ahnenbaum.persistence.entity.CityDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityDBOMapper {
	CityModel toModelClass(CityDBO dbo);

	CityDBO toDBO(City model);

	default City toModel(CityDBO dbo) {
		return toModelClass(dbo);
	}
}
