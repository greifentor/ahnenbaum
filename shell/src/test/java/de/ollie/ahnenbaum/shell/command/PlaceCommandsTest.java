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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
class PlaceCommandsTest {

	private static final String NAME = "name";
	private static final String MAPPED_EXCEPTION_MESSAGE = "mapped-exception-message";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private PlaceService placeService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private PlaceCommands unitUnderTest;

	private Place createModel(String name, UUID uuid) {
		return new Place().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddPlace {

		@Test
		void returnsTheCorrectString_whenPlaceHasBeenCreated() {
			// Prepare
			Place model = createModel(NAME, UID);
			String expected = model.toString();
			when(placeService.create(NAME)).thenReturn(model);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenPlaceCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(PlaceCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(placeService.create(NAME)).thenThrow(thrown);
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
		void returnsTheCorrectString_whenPlacesNameHasBeenChanged() {
			// Prepare
			Place model0 = createModel(NAME, UID);
			Place model1 = createModel(NAME, UID);
			String expected = model1.toString();
			when(placeService.findById(UID)).thenReturn(Optional.of(model0));
			when(placeService.update(model0)).thenReturn(model1);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenNoRecordIsFoundForPassedId() {
			// Prepare
			when(placeService.findById(UID)).thenReturn(Optional.empty());
			when(exceptionToStringMapper.map(any(NoSuchRecordException.class))).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileChangingTheName() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(placeService.findById(UID)).thenThrow(e);
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
		void returnsACorrectString_whenPlaceCouldBeDeleted() {
			// Prepare
			String expected = PlaceCommands.MESSAGE_PLACE_DELETED.replace("{id}", UID.toString());
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileDeletingThePlace() {
			// Prepare
			RuntimeException e = new RuntimeException();
			doThrow(e).when(placeService).deleteById(UID);
			when(exceptionToStringMapper.map(e)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.delete(UID.toString());
			// Check
			assertEquals(MAPPED_EXCEPTION_MESSAGE, returned);
		}

	}

	@Nested
	class TestsOfMethod_FindPlace {

		@Test
		void returnsTheCorrectString_whenPlaceHasBeenCreated() {
			// Prepare
			Place model = createModel(NAME, UID);
			String expected = model.toString();
			when(placeService.findById(model.getId())).thenReturn(Optional.of(model));
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenPlaceIsNotPresent() {
			// Prepare
			Place model = createModel(NAME, UID);
			String expected = PlaceCommands.MESSAGE_NO_PLACE_WITH_ID.replace("{id}", model.getId().toString());
			when(placeService.findById(model.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(model.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenPlaceCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(PlaceCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(placeService.findById(UID)).thenThrow(thrown);
			when(exceptionToStringMapper.map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.findById(UID.toString());
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_ListPlaces {

		@Test
		void returnsTheCorrectString_whenPlacesAreStored() {
			// Prepare
			Place model0 = createModel(NAME + 0, UID);
			Place model1 = createModel(NAME + 1, UUID.randomUUID());
			String expected = model0 + "\n" + model1;
			List<Place> models = List.of(model0, model1);
			when(placeService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoPlacesAreStored() {
			// Prepare
			String expected = PlaceCommands.MESSAGE_NO_DATA;
			List<Place> models = List.of();
			when(placeService.findAll()).thenReturn(models);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
