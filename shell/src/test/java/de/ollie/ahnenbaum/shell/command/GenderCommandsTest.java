package de.ollie.ahnenbaum.shell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.model.impl.GenderModel;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
class GenderCommandsTest {

	private static final String NAME = "name";
	private static final String MAPPED_EXCEPTION_MESSAGE = "mapped-exception-message";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private GenderService genderService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private GenderCommands unitUnderTest;

	private Gender createModel(String name, UUID uuid) {
		return new GenderModel().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddGender {

		@Test
		void returnsTheCorrectString_whenGenderHasBeenCreated() {
			// Prepare
			Gender model = createModel(NAME, UID);
			String expected = model.toString();
			when(genderService.create(NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(GenderCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(genderService.create(NAME)).thenThrow(thrown);
			when(exceptionToStringMapper.map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ChangeName {

		@Test
		void returnsTheCorrectString_whenGendersNameHasBeenChanged() {
			// Prepare
			Gender model = createModel(NAME, UID);
			String expected = model.toString();
			when(genderService.changeName(UID, NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileChangingTheName() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(genderService.changeName(UID, NAME)).thenThrow(e);
			when(exceptionToStringMapper.map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

	}

	@Nested
	class TestsOfMethod_delete {

		@Test
		void returnsACorrectString_whenGenderCouldBeDeleted() {
			// Prepare
			String expected = GenderCommands.MESSAGE_GENDER_DELETED.replace("{id}", UID.toString());
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileDeletingTheGender() {
			// Prepare
			RuntimeException e = new RuntimeException();
			doThrow(e).when(genderService).deleteById(UID);
			when(exceptionToStringMapper.map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

	}

	@Nested
	class TestsOfMethod_FindGender {

		@Test
		void returnsTheCorrectString_whenGenderHasBeenCreated() {
			// Prepare
			Gender model = createModel(NAME, UID);
			String expected = model.toString();
			when(genderService.findById(model.getId())).thenReturn(Optional.of(model));
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderIsNotPresent() {
			// Prepare
			Gender model = createModel(NAME, UID);
			String expected = GenderCommands.MESSAGE_NO_GENDER_WITH_ID.replace("{id}", model.getId().toString());
			when(genderService.findById(model.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenGenderCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(PlaceCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(genderService.findById(UID)).thenThrow(thrown);
			when(exceptionToStringMapper.map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.findById(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ListGenders {

		@Test
		void returnsTheCorrectString_whenGendersAreStored() {
			// Prepare
			Gender model0 = createModel(NAME + 0, UID);
			Gender model1 = createModel(NAME + 1, UUID.randomUUID());
			String expected = model0 + "\n" + model1;
			List<Gender> models = List.of(model0, model1);
			when(genderService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoGendersAreStored() {
			// Prepare
			String expected = GenderCommands.MESSAGE_NO_DATA;
			List<Gender> models = List.of();
			when(genderService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
