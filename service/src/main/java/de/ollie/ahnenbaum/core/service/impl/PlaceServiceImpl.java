package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import jakarta.inject.Named;

@Named
class PlaceServiceImpl extends NameProviderServiceImpl<Place> implements PlaceService {

	public PlaceServiceImpl(PlacePersistencePort persistencePort) {
		super(persistencePort);
	}
}
