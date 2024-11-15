package de.ollie.ahnenbaum.persistence.mapper;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.model.impl.ProfessionModel;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import jakarta.inject.Named;
import lombok.Generated;

@Named
@Generated
public class ProfessionDBOMapper {

	public Profession toModel(ProfessionDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new ProfessionModel().setId(dbo.getId()).setName(dbo.getName());
	}

	public ProfessionDBO toDBO(ProfessionModel model) {
		if (model == null) {
			return null;
		}
		return new ProfessionDBO().setId(model.getId()).setName(model.getName());
	}
}
