package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Person;
import de.ollie.ahnenbaum.core.model.impl.PersonModel;
import de.ollie.ahnenbaum.persistence.entity.PersonDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { GenderDBOMapper.class, PlaceDBOMapper.class })
public interface PersonDBOMapper {
	PersonModel toModelClass(PersonDBO dbo);

	PersonDBO toDBO(Person model);

	default Person toModel(PersonDBO dbo) {
		return toModelClass(dbo);
	}
}
