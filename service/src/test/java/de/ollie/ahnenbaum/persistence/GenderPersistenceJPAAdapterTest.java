package de.ollie.ahnenbaum.persistence;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import de.ollie.ahnenbaum.persistence.factory.GenderDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.GenderDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.GenderDBORepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GenderPersistenceJPAAdapterTest
	extends NameProviderPersistenceJPAAdapterTest<Gender, GenderDBO, GenderPersistenceJPAAdapter, GenderDBORepository, GenderDBOMapper, GenderDBOFactory> {

	@Mock
	private GenderDBOFactory factory;

	@Mock
	private GenderDBOMapper mapper;

	@Mock
	private GenderDBORepository repository;

	@InjectMocks
	private GenderPersistenceJPAAdapter unitUnderTest;

	@Override
	GenderDBO createDBO() {
		return mock(GenderDBO.class);
	}

	@Override
	Gender createModel() {
		return mock(Gender.class);
	}

	@Override
	GenderDBOFactory factory() {
		return factory;
	}

	@Override
	GenderDBOMapper mapper() {
		return mapper;
	}

	@Override
	GenderDBORepository repository() {
		return repository;
	}

	@Override
	GenderPersistenceJPAAdapter unitUnderTest() {
		return unitUnderTest;
	}
}
