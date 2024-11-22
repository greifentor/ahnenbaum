package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.model.impl.PlaceModel;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceDBOMapper {
	@BeanMapping(resultType = PlaceModel.class)
	Place toModel(PlaceDBO dbo);

	PlaceDBO toDBO(Place model);
}
