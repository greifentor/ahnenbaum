package de.ollie.ahnenbaum.core.service.impl;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfessionServiceImplTest extends NameProviderServiceImplTest<Profession> {

	@Mock
	private ProfessionPersistencePort persistencePort;

	@InjectMocks
	private ProfessionServiceImpl unitUnderTest;

	@Override
	Profession createModel() {
		return mock(Profession.class);
	}

	@Override
	NameProviderPersistencePort<Profession> persistencePort() {
		return persistencePort;
	}

	@Override
	NameProviderServiceImpl<Profession> unitUnderTest() {
		return unitUnderTest;
	}
}
