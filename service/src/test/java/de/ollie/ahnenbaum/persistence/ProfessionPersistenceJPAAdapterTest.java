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
import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import de.ollie.ahnenbaum.persistence.factory.ProfessionDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.ProfessionDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.ProfessionDBORepository;
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
class ProfessionPersistenceJPAAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private Profession model0;

	@Mock
	private Profession model1;

	@Mock
	private ProfessionDBO dbo0;

	@Mock
	private ProfessionDBO dbo1;

	@Mock
	private ProfessionDBOFactory factory;

	@Mock
	private ProfessionDBOMapper mapper;

	@Mock
	private ProfessionDBORepository repository;

	@InjectMocks
	private ProfessionPersistenceJPAAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_changeName_UUID_String {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.changeName(null, NAME));
		}

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.changeName(UID, null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(ParameterIsBlankException.class, () -> unitUnderTest.changeName(UID, ""));
		}

		@Test
		void callsDBOSetNameAndRepositorySave() {
			// Prepare
			when(dbo0.setName(NAME)).thenReturn(dbo0);
			when(repository.findById(UID)).thenReturn(Optional.of(dbo0));
			when(repository.save(dbo0)).thenReturn(dbo0);
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run
			Profession returned = unitUnderTest.changeName(UID, NAME);
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
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingABlankString() {
			assertThrows(ParameterIsBlankException.class, () -> unitUnderTest.create("\r\n\t "));
		}

		@Test
		void throwsAnException_passingAnAlreadyStoredNameAGain() {
			// Prepare
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo0));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(NAME));
		}

		@Test
		void returnsANewProfession() {
			// Prepare
			when(factory.create(NAME)).thenReturn(dbo0);
			when(repository.findByName(NAME)).thenReturn(Optional.empty());
			when(repository.save(dbo0)).thenReturn(dbo0);
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run
			Profession returned = unitUnderTest.create(NAME);
			// Check
			assertSame(model0, returned);
		}
	}

	@Nested
	class TestsOfMethod_deleteById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.deleteById(null));
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
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.findById(null));
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
