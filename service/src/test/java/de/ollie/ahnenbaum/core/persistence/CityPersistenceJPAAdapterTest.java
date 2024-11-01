package de.ollie.ahnenbaum.core.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.core.persistence.factory.CityDBOFactory;
import de.ollie.ahnenbaum.core.persistence.mapper.CityDBOMapper;
import de.ollie.ahnenbaum.core.persistence.repository.CityDBORepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityPersistenceJPAAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

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

	@Nested
	class TestsOfMethod_changeName_String {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.changeName(null, NAME));
		}

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.changeName(UID, null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.changeName(UID, ""));
		}

		@Test
		void callsThePersistentPortUpdate() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.of(dbo));
			// Run
			unitUnderTest.changeName(UID, NAME);
			// Check
			verify(repository, times(1)).save(dbo);
		}

		@Test
		void callsTheDbosSetNameMethod() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.of(dbo));
			// Run
			unitUnderTest.changeName(UID, NAME);
			// Check
			verify(dbo, times(1)).setName(NAME);
		}

		@Test
		void throwsAnException_whenThereIsNoDboForThePassedId() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.empty());
			// Run
			assertThrows(NoSuchElementException.class, () -> unitUnderTest.changeName(UID, NAME));
			// Check
			verifyNoInteractions(dbo);
		}
	}

	@Nested
	class TestsOfMethod_findById_String {

		@Test
		void throwsAnException_passingANullPointer() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void returnsTheMappedReturnOfTheRepositoryCall() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.of(dbo));
			when(mapper.toModel(dbo)).thenReturn(model);
			// Run
			assertSame(model, unitUnderTest.findById(UID).get());
		}
	}
}
