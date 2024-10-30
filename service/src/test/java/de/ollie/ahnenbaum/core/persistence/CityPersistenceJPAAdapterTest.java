package de.ollie.ahnenbaum.core.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.core.persistence.factory.CityDBOFactory;
import de.ollie.ahnenbaum.core.persistence.mapper.CityDBOMapper;
import de.ollie.ahnenbaum.core.persistence.repository.CityDBORepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityPersistenceJPAAdapterTest {

	private static final String NAME = "name";

	@Mock
	private City model;

	@Mock
	private CityDBO dbo;

	@Mock
	private CityDBOFactory factory;

	@Mock
	private CityDBOMapper mapper;

	@Mock
	private CityDBORepository repository;

	@InjectMocks
	private CityPersistenceJPAAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_create {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingABlankString() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create("\r\n\t "));
		}

		@Test
		void returnsANewCity() {
			// Prepare
			when(factory.create(NAME)).thenReturn(dbo);
			when(repository.save(dbo)).thenReturn(dbo);
			when(mapper.toModel(dbo)).thenReturn(model);
			// Run
			City returned = unitUnderTest.create(NAME);
			// Check
			assertSame(model, returned);
		}
	}
}
