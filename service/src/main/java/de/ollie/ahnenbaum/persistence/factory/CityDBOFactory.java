package de.ollie.ahnenbaum.persistence.factory;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.CityDBO;
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
