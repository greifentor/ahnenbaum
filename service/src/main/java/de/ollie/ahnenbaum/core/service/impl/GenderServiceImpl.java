package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import jakarta.inject.Named;

@Named
class GenderServiceImpl extends NameProviderServiceImpl<Gender> implements GenderService {

	public GenderServiceImpl(GenderPersistencePort persistencePort) {
		super(persistencePort);
	}
}
