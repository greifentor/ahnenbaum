package de.ollie.ahnenbaum.persistence.factory;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ProfessionDBOFactory {

	private final UUIDService uuidService;

	public ProfessionDBO create(String name) {
		return new ProfessionDBO().setId(uuidService.create()).setName(name);
	}
}