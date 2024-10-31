package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.port.CityPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private City city;

	@Mock
	private CityPersistencePort cityPersistencePort;

	@InjectMocks
	private CityServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_changeName_String {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ServiceException.class, () -> unitUnderTest.changeName(null, NAME));
		}

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.changeName(UID, null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.changeName(UID, ""));
		}

		@Test
		void changesTheNameByCallingThePersistencePortCorrectly() {
			// Prepare
			when(cityPersistencePort.findById(UID)).thenReturn(Optional.of(city));
			// Run
			unitUnderTest.changeName(UID, NAME);
			// Check
			verify(cityPersistencePort, times(1)).changeName(UID, NAME);
		}
	}

	@Nested
	class TestsOfMethod_create_String {

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.create(""));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			when(cityPersistencePort.create(NAME)).thenReturn(city);
			// Run
			City returned = unitUnderTest.create(NAME);
			// Check
			assertSame(city, returned);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			List<City> expected = List.of(city);
			when(cityPersistencePort.findAll()).thenReturn(expected);
			// Run
			List<City> returned = unitUnderTest.findAll();
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_findById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ServiceException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			Optional<City> expected = Optional.of(city);
			when(cityPersistencePort.findById(UID)).thenReturn(expected);
			// Run
			Optional<City> returned = unitUnderTest.findById(UID);
			// Check
			assertSame(expected, returned);
		}
	}
}