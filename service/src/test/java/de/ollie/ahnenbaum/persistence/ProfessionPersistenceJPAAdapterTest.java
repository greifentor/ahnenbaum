package de.ollie.ahnenbaum.persistence;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import de.ollie.ahnenbaum.persistence.factory.DBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.ProfessionDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.ProfessionDBORepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfessionPersistenceJPAAdapterTest
	extends NameProviderPersistenceJPAAdapterTest<Profession, ProfessionDBO, ProfessionPersistenceJPAAdapter, ProfessionDBORepository, ProfessionDBOMapper> {

	@Mock
	private DBOFactory<ProfessionDBO> factory;

	@Mock
	private ProfessionDBOMapper mapper;

	@Mock
	private ProfessionDBORepository repository;

	@InjectMocks
	private ProfessionPersistenceJPAAdapter unitUnderTest;

	@Override
	ProfessionDBO createDBO() {
		return mock(ProfessionDBO.class);
	}

	@Override
	Profession createModel() {
		return mock(Profession.class);
	}

	@Override
	DBOFactory<ProfessionDBO> factory() {
		return factory;
	}

	@Override
	ProfessionDBOMapper mapper() {
		return mapper;
	}

	@Override
	ProfessionDBORepository repository() {
		return repository;
	}

	@Override
	ProfessionPersistenceJPAAdapter unitUnderTest() {
		return unitUnderTest;
	}
}
