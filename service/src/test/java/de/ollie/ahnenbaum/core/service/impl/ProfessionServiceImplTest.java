package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
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
class ProfessionServiceImplTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private Profession Profession;

	@Mock
	private ProfessionPersistencePort ProfessionPersistencePort;

	@InjectMocks
	private ProfessionServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_changeName_String {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.changeName(null, NAME));
		}

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.changeName(UID, null));
		}

		@Test
		void throwsAnException_passingABlankStringAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.changeName(UID, "\t\n\r "));
		}

		@Test
		void throwsAnException_whenPersistencePortThrowsAnException() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(ProfessionPersistencePort.changeName(UID, NAME)).thenThrow(e);
			// Run & Check
			Exception thrown = assertThrows(RuntimeException.class, () -> unitUnderTest.changeName(UID, NAME));
			assertSame(thrown, e);
		}

		@Test
		void changesTheNameByCallingThePersistencePortCorrectly() {
			// Prepare
			when(ProfessionPersistencePort.changeName(UID, NAME)).thenReturn(Profession);
			// Run
			Profession returned = unitUnderTest.changeName(UID, NAME);
			// Check
			assertEquals(Profession, returned);
		}
	}

	@Nested
	class TestsOfMethod_create_String {

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest.create(""));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			when(ProfessionPersistencePort.create(NAME)).thenReturn(Profession);
			// Run
			Profession returned = unitUnderTest.create(NAME);
			// Check
			assertSame(Profession, returned);
		}
	}

	@Nested
	class TestsOfMethod_deleteById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.deleteById(null));
		}

		@Test
		void callsThePersistencePortMethodDeleteByIdCorrectly() {
			// Run
			unitUnderTest.deleteById(UID);
			// Check
			verify(ProfessionPersistencePort, times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			List<Profession> expected = List.of(Profession);
			when(ProfessionPersistencePort.findAll()).thenReturn(expected);
			// Run
			List<Profession> returned = unitUnderTest.findAll();
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_findById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			Optional<Profession> expected = Optional.of(Profession);
			when(ProfessionPersistencePort.findById(UID)).thenReturn(expected);
			// Run
			Optional<Profession> returned = unitUnderTest.findById(UID);
			// Check
			assertSame(expected, returned);
		}
	}
}
