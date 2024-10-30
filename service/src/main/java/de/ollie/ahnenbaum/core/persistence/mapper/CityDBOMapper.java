package de.ollie.ahnenbaum.core.persistence.mapper;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.model.impl.CityModel;
import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import jakarta.inject.Named;

@Named
public class CityDBOMapper {

	public City toModel(CityDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new CityModel().setId(dbo.getId()).setName(dbo.getName());
	}

	public CityDBO toDBO(CityModel model) {
		if (model == null) {
			return null;
		}
		return new CityDBO().setId(model.getId()).setName(model.getName());
	}
}
