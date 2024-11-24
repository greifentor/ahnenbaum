package de.ollie.ahnenbaum.core.service.impl;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GenderServiceImplTest extends NameProviderServiceImplTest<Gender> {

	@Mock
	private GenderPersistencePort persistencePort;

	@Mock
	private UUIDService uuidService;

	@InjectMocks
	private GenderServiceImpl unitUnderTest;

	@Override
	Gender createModel() {
		return mock(Gender.class);
	}

	@Override
	NameProviderPersistencePort<Gender> persistencePort() {
		return persistencePort;
	}

	@Override
	NameProviderServiceImpl<Gender> unitUnderTest() {
		return unitUnderTest;
	}
}
