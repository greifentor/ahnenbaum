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
import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.model.impl.ProfessionModel;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
public class ProfessionCommandsTest {

	private static final String NAME = "name";
	private static final String MAPPED_EXCEPTION_MESSAGE = "mapped-exception-message";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private ProfessionService professionService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private ProfessionCommands unitUnderTest;

	private Profession createModel(String name, UUID uuid) {
		return new ProfessionModel().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddProfession {

		@Test
		void returnsTheCorrectString_whenProfessionHasBeenCreated() {
			// Prepare
			Profession model = createModel(NAME, UID);
			String expected = model.toString();
			when(professionService.create(NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenProfessionCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(ProfessionCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(professionService.create(NAME)).thenThrow(thrown);
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
		void returnsTheCorrectString_whenProfessionNameHasBeenChanged() {
			// Prepare
			Profession model = createModel(NAME, UID);
			String expected = model.toString();
			when(professionService.changeName(UID, NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileChangingTheName() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(professionService.changeName(UID, NAME)).thenThrow(e);
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
		void returnsACorrectString_whenProfessionCouldBeDeleted() {
			// Prepare
			String expected = ProfessionCommands.MESSAGE_PROFESSION_DELETED.replace("{id}", UID.toString());
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileDeletingTheProfession() {
			// Prepare
			RuntimeException e = new RuntimeException();
			doThrow(e).when(professionService).deleteById(UID);
			when(exceptionToStringMapper.map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

	}

	@Nested
	class TestsOfMethod_FindProfession {

		@Test
		void returnsTheCorrectString_whenProfessionHasBeenCreated() {
			// Prepare
			Profession model = createModel(NAME, UID);
			String expected = model.toString();
			when(professionService.findById(model.getId())).thenReturn(Optional.of(model));
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenProfessionIsNotPresent() {
			// Prepare
			Profession model = createModel(NAME, UID);
			String expected = ProfessionCommands.MESSAGE_NO_PROFESSION_WITH_ID.replace("{id}",
					model.getId().toString());
			when(professionService.findById(model.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenProfessionCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(ProfessionCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(professionService.findById(UID)).thenThrow(thrown);
			when(exceptionToStringMapper.map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.findById(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ListCities {

		@Test
		void returnsTheCorrectString_whenProfessionsAreStored() {
			// Prepare
			Profession model0 = createModel(NAME + 0, UID);
			Profession model1 = createModel(NAME + 1, UUID.randomUUID());
			String expected = model0 + "\n" + model1;
			List<Profession> models = List.of(model0, model1);
			when(professionService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoProfessionsAreStored() {
			// Prepare
			String expected = ProfessionCommands.MESSAGE_NO_DATA;
			List<Profession> models = List.of();
			when(professionService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
