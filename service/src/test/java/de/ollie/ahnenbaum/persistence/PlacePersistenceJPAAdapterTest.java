package de.ollie.ahnenbaum.persistence;

import static org.mockito.Mockito.mock;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import de.ollie.ahnenbaum.persistence.factory.DBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.PlaceDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.PlaceDBORepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlacePersistenceJPAAdapterTest
	extends NameProviderPersistenceJPAAdapterTest<Place, PlaceDBO, PlacePersistenceJPAAdapter, PlaceDBORepository, PlaceDBOMapper> {

	@Mock
	private DBOFactory<PlaceDBO> factory;

	@Mock
	private PlaceDBOMapper mapper;

	@Mock
	private PlaceDBORepository repository;

	@InjectMocks
	private PlacePersistenceJPAAdapter unitUnderTest;

	@Override
	PlaceDBO createDBO() {
		return mock(PlaceDBO.class);
	}

	@Override
	Place createModel() {
		return mock(Place.class);
	}

	@Override
	DBOFactory<PlaceDBO> factory() {
		return factory;
	}

	@Override
	PlaceDBOMapper mapper() {
		return mapper;
	}

	@Override
	PlaceDBORepository repository() {
		return repository;
	}

	@Override
	PlacePersistenceJPAAdapter unitUnderTest() {
		return unitUnderTest;
	}
}
