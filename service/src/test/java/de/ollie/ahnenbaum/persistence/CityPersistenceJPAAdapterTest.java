package de.ollie.ahnenbaum.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.persistence.entity.CityDBO;
import de.ollie.ahnenbaum.persistence.factory.CityDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.CityDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.CityDBORepository;
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
class CityPersistenceJPAAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private City model0;

	@Mock
	private City model1;

	@Mock
	private CityDBO dbo0;

	@Mock
	private CityDBO dbo1;

	@Mock
	private CityDBOFactory factory;

	@Mock
	private CityDBOMapper mapper;

	@Mock
	private CityDBORepository repository;

	@InjectMocks
	private CityPersistenceJPAAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_changeName_UUID_String {

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
		void callsDBOSetNameAndRepositorySave() {
			// Prepare
			when(dbo0.setName(NAME)).thenReturn(dbo0);
			when(repository.findById(UID)).thenReturn(Optional.of(dbo0));
			when(repository.save(dbo0)).thenReturn(dbo0);
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run
			City returned = unitUnderTest.changeName(UID, NAME);
			// Check
			assertEquals(model0, returned);
		}

		@Test
		void throwsAnException_whenThereIsNoDboForThePassedId() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.empty());
			// Run
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.changeName(UID, NAME));
			// Check
			verifyNoInteractions(dbo0);
		}
	}

	@Nested
	class TestsOfMethod_create_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingABlankString() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create("\r\n\t "));
		}

		@Test
		void throwsAnException_passingAnAlreadyStoredNameAGain() {
			// Prepare
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo0));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(NAME));
		}

		@Test
		void returnsANewCity() {
			// Prepare
			when(factory.create(NAME)).thenReturn(dbo0);
			when(repository.findByName(NAME)).thenReturn(Optional.empty());
			when(repository.save(dbo0)).thenReturn(dbo0);
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run
			City returned = unitUnderTest.create(NAME);
			// Check
			assertSame(model0, returned);
		}
	}

	@Nested
	class TestsOfMethod_deleteById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.deleteById(null));
		}

		@Test
		void callsTheDeleteByIdMethodOfTheRepositoryCorrectly() {
			// Run
			unitUnderTest.deleteById(UID);
			// Check
			verify(repository, times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void returnsAllMappedCitiesFromReadRepository() {
			// Prepare
			when(repository.findAll()).thenReturn(List.of(dbo0, dbo1));
			when(mapper.toModel(dbo0)).thenReturn(model0);
			when(mapper.toModel(dbo1)).thenReturn(model1);
			// Run & Check
			assertEquals(List.of(model0, model1), unitUnderTest.findAll());
		}

		@Test
		void returnsAnEmptyList_whenListReturnedFromRepositoryIsEmpty() {
			// Prepare
			when(repository.findAll()).thenReturn(List.of());
			// Run & Check
			assertTrue(unitUnderTest.findAll().isEmpty());
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
			when(repository.findById(UID)).thenReturn(Optional.of(dbo0));
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run & Check
			assertSame(model0, unitUnderTest.findById(UID).get());
		}
	}
}
