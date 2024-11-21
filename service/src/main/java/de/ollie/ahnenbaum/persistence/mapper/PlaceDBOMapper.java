package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.model.impl.PlaceModel;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceDBOMapper {
	PlaceModel toModelClass(PlaceDBO dbo);

	PlaceDBO toDBO(Place model);

	default Place toModel(PlaceDBO dbo) {
		return toModelClass(dbo);
	}
}
