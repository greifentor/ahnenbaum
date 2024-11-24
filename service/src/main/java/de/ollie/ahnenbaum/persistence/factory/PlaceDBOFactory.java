package de.ollie.ahnenbaum.persistence.factory;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class PlaceDBOFactory implements DBOFactory<PlaceDBO> {

	private final UUIDService uuidService;

	@Override
	public PlaceDBO create(String name) {
		return new PlaceDBO().setId(uuidService.create()).setName(name);
	}
}
