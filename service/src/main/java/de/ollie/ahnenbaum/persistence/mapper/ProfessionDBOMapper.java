package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionDBOMapper extends DBOMapper<Profession, ProfessionDBO> {}
