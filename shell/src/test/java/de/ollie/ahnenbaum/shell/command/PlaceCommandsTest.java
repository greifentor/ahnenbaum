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
import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.model.impl.PlaceModel;
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

	private Place createPlace(String name, UUID uuid) {
		return new PlaceModel().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddPlace {

		@Test
		void returnsTheCorrectString_whenPlaceHasBeenCreated() {
			// Prepare
			Place place = createPlace(NAME, UID);
			String expected = place.toString();
			when(placeService.create(NAME)).thenReturn(place);
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
			Place place = createPlace(NAME, UID);
			String expected = place.toString();
			when(placeService.changeName(UID, NAME)).thenReturn(place);
			// Run
			String returned = unitUnderTest.changeName(UID.toString(), NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsExceptionString_whenSomethingWentWrongWhileChangingTheName() {
			// Prepare
			RuntimeException e = new RuntimeException();
			when(placeService.changeName(UID, NAME)).thenThrow(e);
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
			Place place = createPlace(NAME, UID);
			String expected = place.toString();
			when(placeService.findById(place.getId())).thenReturn(Optional.of(place));
			// Run
			String returned = unitUnderTest.findById(place.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenPlaceIsNotPresent() {
			// Prepare
			Place place = createPlace(NAME, UID);
			String expected = PlaceCommands.MESSAGE_NO_PLACE_WITH_ID.replace("{id}", place.getId().toString());
			when(placeService.findById(place.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(place.getId().toString());
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
			Place place0 = createPlace(NAME + 0, UID);
			Place place1 = createPlace(NAME + 1, UUID.randomUUID());
			String expected = place0 + "\n" + place1;
			List<Place> cities = List.of(place0, place1);
			when(placeService.findAll()).thenReturn(cities);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoPlacesAreStored() {
			// Prepare
			String expected = PlaceCommands.MESSAGE_NO_DATA;
			List<Place> cities = List.of();
			when(placeService.findAll()).thenReturn(cities);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
