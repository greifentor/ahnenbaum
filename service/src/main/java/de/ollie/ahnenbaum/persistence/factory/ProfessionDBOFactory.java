package de.ollie.ahnenbaum.persistence.factory;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class ProfessionDBOFactory implements DBOFactory<ProfessionDBO> {

	private final UUIDService uuidService;

	@Override
	public ProfessionDBO create(String name) {
		return new ProfessionDBO().setId(uuidService.create()).setName(name);
	}
}
