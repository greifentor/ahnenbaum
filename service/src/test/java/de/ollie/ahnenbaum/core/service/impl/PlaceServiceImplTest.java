package de.ollie.ahnenbaum.core.service.impl;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlaceServiceImplTest extends NameProviderServiceImplTest<Place> {

	@Mock
	private PlacePersistencePort persistencePort;

	@InjectMocks
	private PlaceServiceImpl unitUnderTest;

	@Override
	Place createModel() {
		return mock(Place.class);
	}

	@Override
	NameProviderPersistencePort<Place> persistencePort() {
		return persistencePort;
	}

	@Override
	NameProviderServiceImpl<Place> unitUnderTest() {
		return unitUnderTest;
	}
}
