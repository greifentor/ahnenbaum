package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.model.impl.ProfessionModel;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionDBOMapper {
	@BeanMapping(resultType = ProfessionModel.class)
	Profession toModel(ProfessionDBO dbo);

	ProfessionDBO toDBO(ProfessionModel model);
}
