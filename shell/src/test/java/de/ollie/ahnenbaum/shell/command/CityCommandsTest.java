package de.ollie.ahnenbaum.shell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.model.impl.CityModel;
import de.ollie.ahnenbaum.core.service.CityService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
class CityCommandsTest {

	private static final String NAME = "name";
	private static final String MAPPED_EXCEPTION_MESSAGE = "mapped-exception-message";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private CityService cityService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private CityCommands unitUnderTest;

	private City createCity(String name, UUID uuid) {
		return new CityModel().setId(uuid).setName(name);
	}

	@Nested
	class TestsOfMethod_AddCity {

		@Test
		void returnsTheCorrectString_whenCityHasBeenCreated() {
			// Prepare
			City city = createCity(NAME, UID);
			String expected = city.toString();
			when(cityService.create(NAME)).thenReturn(city);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenCityCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(CityCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(cityService.create(NAME)).thenThrow(thrown);
			when(exceptionToStringMapper.map(thrown)).thenReturn(MAPPED_EXCEPTION_MESSAGE);
			// Run
			String returned = unitUnderTest.add(NAME);
			// Check
			assertEquals(expected, returned);
		}

	}

	@Nested
	class TestsOfMethod_FindCity {

		@Test
		void returnsTheCorrectString_whenCityHasBeenCreated() {
			// Prepare
			City city = createCity(NAME, UID);
			String expected = city.toString();
			when(cityService.findById(city.getId())).thenReturn(Optional.of(city));
			// Run
			String returned = unitUnderTest.findById(city.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenCityIsNotPresent() {
			// Prepare
			City city = createCity(NAME, UID);
			String expected = CityCommands.MESSAGE_NO_CITY_WITH_ID.replace("{id}", city.getId().toString());
			when(cityService.findById(city.getId())).thenReturn(Optional.empty());
			// Run
			String returned = unitUnderTest.findById(city.getId().toString());
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenCityCanNotBeCreated_byAlreadyExistingName() {
			// Prepare
			String expected = MAPPED_EXCEPTION_MESSAGE;
			ServiceException thrown = new ServiceException(CityCommands.MESSAGE_NAME_ALREADY_EXISTING, null, "");
			when(cityService.findById(UID)).thenThrow(thrown);
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
			City city0 = createCity(NAME + 0, UID);
			City city1 = createCity(NAME + 1, UUID.randomUUID());
			String expected = city0 + "\n" + city1;
			List<City> cities = List.of(city0, city1);
			when(cityService.findAll()).thenReturn(cities);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsTheCorrectString_whenNoCitiesAreStored() {
			// Prepare
			String expected = CityCommands.MESSAGE_NO_DATA;
			List<City> cities = List.of();
			when(cityService.findAll()).thenReturn(cities);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
