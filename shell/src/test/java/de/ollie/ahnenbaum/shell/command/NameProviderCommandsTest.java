package de.ollie.ahnenbaum.shell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.NameProvider;
import de.ollie.ahnenbaum.core.service.NameProviderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
abstract class NameProviderCommandsTest<M extends NameProvider, S extends NameProviderService<M>, C extends NameProviderCommands<M, S>> {

	private static final String NAME = "name";
	private static final String MAPPED_EXCEPTION_MESSAGE = "mapped-exception-message";
	private static final String ORIGINAL_MAPPED_EXCEPTION_MESSAGE = "original-mapped-exception-message";
	private static final UUID UID = UUID.randomUUID();

	abstract S nameProviderService();

	abstract ExceptionToStringMapper exceptionToStringMapper();

	abstract M createModel(String name, UUID uuid);

	abstract String messageNoData();

	abstract String messageNoRecordWithId();

	abstract String messageRecordDeleted();

	abstract C unitUnderTest();

	@Nested
	class TestsOfMethod_AddRecord {

		@Test
		void returnsTheCorrectString_whenRecordHasBeenCreated() {
			// Prepare
			M model = createModel(NAME, UID);
			String expected = model.toString();
			when(nameProviderService().create(NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest().add(NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(ORIGINAL_MAPPED_EXCEPTION_MESSAGE, null, "");
			when(nameProviderService().create(NAME)).thenThrow(thrown);
			when(exceptionToStringMapper().map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest().add(NAME);
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ChangeName {

		@Test
		void returnsTheCorrectString_whenGendersNameHasBeenChanged() {
			// Prepare
			M model0 = createModel(NAME, UID);
			M model1 = createModel(NAME, UID);
			String expected = model1.toString();
			when(nameProviderService().findById(UID)).thenReturn(Optional.of(model0));
			when(nameProviderService().update(model0)).thenReturn(model1);
			// Run
			String returned = unitUnderTest().changeName(UID.toString(), NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenNoRecordIsFoundForPassedId() {
			// Prepare
			when(nameProviderService().findById(UID)).thenReturn(Optional.empty());
			when(exceptionToStringMapper().map(any(NoSuchRecordException.class))).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest().changeName(UID.toString(), NAME);
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileChangingTheName() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(nameProviderService().findById(UID)).thenThrow(e);
			when(exceptionToStringMapper().map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest().changeName(UID.toString(), NAME);
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}
	}

	@Nested
	class TestsOfMethod_delete {

		@Test
		void returnsACorrectString_whenGenderCouldBeDeleted() {
			// Prepare
			String expected = messageRecordDeleted().replace("{id}", UID.toString());
			// Run
			String returned = unitUnderTest().delete(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileDeletingTheGender() {
			// Prepare
			RuntimeException e = new RuntimeException();
			doThrow(e).when(nameProviderService()).deleteById(UID);
			when(exceptionToStringMapper().map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest().delete(UID.toString());
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

	}

	@Nested
	class TestsOfMethod_FindGender {

		@Test
		void returnsTheCorrectString_whenGenderHasBeenCreated() {
			// Prepare
			M model = createModel(NAME, UID);
			String expected = model.toString();
			when(nameProviderService().findById(model.getId())).thenReturn(Optional.of(model));
			// Run
			String returned = unitUnderTest().findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderIsNotPresent() {
			// Prepare
			M model = createModel(NAME, UID);
			String expected = messageNoRecordWithId().replace("{id}", model.getId().toString());
			when(nameProviderService().findById(model.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest().findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(ORIGINAL_MAPPED_EXCEPTION_MESSAGE, null, "");
			when(nameProviderService().findById(UID)).thenThrow(thrown);
			when(exceptionToStringMapper().map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest().findById(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ListGenders {

		@Test
		void returnsTheCorrectString_whenGendersAreStored() {
			// Prepare
			M model0 = createModel(NAME + 0, UID);
			M model1 = createModel(NAME + 1, UUID.randomUUID());
			String expected = model0 + "\n" + model1;
			List<M> models = List.of(model0, model1);
			when(nameProviderService().findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest().list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoGendersAreStored() {
			// Prepare
			String expected = messageNoData();
			List<M> models = List.of();
			when(nameProviderService().findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest().list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
