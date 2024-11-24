package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
import jakarta.inject.Named;

@Named
class ProfessionServiceImpl extends NameProviderServiceImpl<Profession> implements ProfessionService {

	public ProfessionServiceImpl(ProfessionPersistencePort persistencePort) {
		super(persistencePort);
	}
}
