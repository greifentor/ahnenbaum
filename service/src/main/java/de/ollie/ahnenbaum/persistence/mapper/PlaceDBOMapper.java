package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceDBOMapper extends DBOMapper<Place, PlaceDBO> {}
