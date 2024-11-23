package de.ollie.ahnenbaum.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.exception.ParameterIsBlankException;
import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import de.ollie.ahnenbaum.persistence.factory.PlaceDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.PlaceDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.PlaceDBORepository;
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
class PlacePersistenceJPAAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID0 = UUID.randomUUID();
	private static final UUID UID1 = UUID.randomUUID();

	@Mock
	private Place model0;

	@Mock
	private Place model1;

	@Mock
	private PlaceDBO dbo0;

	@Mock
	private PlaceDBO dbo1;

	@Mock
	private PlaceDBOFactory factory;

	@Mock
	private PlaceDBOMapper mapper;

	@Mock
	private PlaceDBORepository repository;

	@InjectMocks
	private PlacePersistenceJPAAdapter unitUnderTest;

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
		void returnsANewRecord() {
			// Prepare
			when(factory.create(NAME)).thenReturn(dbo0);
			when(repository.findByName(NAME)).thenReturn(Optional.empty());
			when(repository.save(dbo0)).thenReturn(dbo0);
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run
			Place returned = unitUnderTest.create(NAME);
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
			unitUnderTest.deleteById(UID0);
			// Check
			verify(repository, times(1)).deleteById(UID0);
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
			when(repository.findById(UID0)).thenReturn(Optional.of(dbo0));
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run & Check
			assertSame(model0, unitUnderTest.findById(UID0).get());
		}
	}

	@Nested
	class TestsOfMethod_findByName_String {

		@Test
		void throwsAnException_passingANullPointer() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.findByName(null));
		}

		@Test
		void returnsTheMappedReturnOfTheRepositoryCall() {
			// Prepare
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo0));
			when(mapper.toModel(dbo0)).thenReturn(model0);
			// Run & Check
			assertSame(model0, unitUnderTest.findByName(NAME).get());
		}
	}

	@Nested
	class TestsOfMethod_update_Gender {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest.update(null));
		}

		@Test
		void throwsAnException_passingAGenderWithAnAlreadyExistingNameButUnmatchingId() {
			// Prepare
			when(dbo0.getId()).thenReturn(UID0);
			when(model0.getName()).thenReturn(NAME);
			when(model0.getId()).thenReturn(UID1);
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo0));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.update(model0));
		}

		@Test
		void callsRepositoryMethodSaveCorrectly() {
			// Prepare
			when(dbo0.getId()).thenReturn(UID0);
			when(model0.getName()).thenReturn(NAME);
			when(model0.getId()).thenReturn(UID0);
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo0));
			when(repository.save(dbo0)).thenReturn(dbo1);
			when(mapper.toDBO(model0)).thenReturn(dbo0);
			when(mapper.toModel(dbo1)).thenReturn(model1);
			// Run
			Place returned = unitUnderTest.update(model0);
			// Check
			assertSame(model1, returned);
		}
	}
}
