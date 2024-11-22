package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderDBOMapper {
	Gender toModel(GenderDBO dbo);

	GenderDBO toDBO(Gender model);
}
