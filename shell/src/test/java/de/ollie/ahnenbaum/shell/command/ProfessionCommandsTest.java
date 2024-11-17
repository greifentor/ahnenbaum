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

	private Profession createProfession(String name, UUID uuid) {
		return new ProfessionModel().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddProfession {

		@Test
		void returnsTheCorrectString_whenProfessionHasBeenCreated() {
			// Prepare
			Profession profession = createProfession(NAME, UID);
			String expected = profession.toString();
			when(professionService.create(NAME)).thenReturn(profession);
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
		void returnsTheCorrectString_whenCitiesNameHasBeenChanged() {
			// Prepare
			Profession profession = createProfession(NAME, UID);
			String expected = profession.toString();
			when(professionService.changeName(UID, NAME)).thenReturn(profession);
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
			Profession profession = createProfession(NAME, UID);
			String expected = profession.toString();
			when(professionService.findById(profession.getId())).thenReturn(Optional.of(profession));
			// Run
			String returned = unitUnderTest.findById(profession.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenProfessionIsNotPresent() {
			// Prepare
			Profession profession = createProfession(NAME, UID);
			String expected = ProfessionCommands.MESSAGE_NO_PROFESSION_WITH_ID.replace("{id}",
					profession.getId().toString());
			when(professionService.findById(profession.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(profession.getId().toString());
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
		void returnsTheCorrectString_whenCitiesAreStored() {
			// Prepare
			Profession profession0 = createProfession(NAME + 0, UID);
			Profession profession1 = createProfession(NAME + 1, UUID.randomUUID());
			String expected = profession0 + "\n" + profession1;
			List<Profession> professions = List.of(profession0, profession1);
			when(professionService.findAll()).thenReturn(professions);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoCitiesAreStored() {
			// Prepare
			String expected = ProfessionCommands.MESSAGE_NO_DATA;
			List<Profession> professions = List.of();
			when(professionService.findAll()).thenReturn(professions);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
