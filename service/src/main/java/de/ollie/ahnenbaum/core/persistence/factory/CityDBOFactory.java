package de.ollie.ahnenbaum.core.persistence.factory;

import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.core.service.UUIDService;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CityDBOFactory {

	private final UUIDService uuidService;

	public CityDBO create(String name) {
		return new CityDBO().setId(uuidService.create()).setName(name);
	}
}
