package de.ollie.ahnenbaum.shell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.model.impl.CityModel;
import de.ollie.ahnenbaum.core.service.CityService;

@ExtendWith(MockitoExtension.class)
class CityCommandsTest {

	private static final String NAME = "name";

	@Mock
	private CityService cityService;
	@InjectMocks
	private CityCommands unitUnderTest;

	private City createCity(String name) {
		return new CityModel().setName(NAME);
	}

	@Nested
	class TestsOfMethod_ListCities {

		@Test
		void returnsTheCorrectString_whenCitiesAreStored() {
			// Prepare
			City city0 = createCity(NAME + 0);
			City city1 = createCity(NAME + 1);
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
			City city0 = createCity(NAME + 0);
			City city1 = createCity(NAME + 1);
			String expected = city0 + "\n" + city1;
			List<City> cities = List.of(city0, city1);
			when(cityService.findAll()).thenReturn(cities);
			// Run
			String returned = unitUnderTest.list();
			// Check
			assertEquals(expected, returned);
		}

	}

}
