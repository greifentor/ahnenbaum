package de.ollie.ahnenbaum.persistence.factory;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class GenderDBOFactory {

	private final UUIDService uuidService;

	public GenderDBO create(String name) {
		return new GenderDBO().setId(uuidService.create()).setName(name);
	}
}
