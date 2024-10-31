package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.ahnenbaum.core.model.City;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CityServiceImplITest {

	private static final String NAME = "name";

	@Inject
	private CityServiceImpl unitUnderTest;

	@Test
	void createsANewCityWithPassedName() {
		City city = unitUnderTest.create(NAME);
		assertEquals(NAME, city.getName());
		City returned = unitUnderTest.findById(city.getId()).get();
		assertEquals(NAME, returned.getName());
	}

	// @Test
	void createsANewCityWithPassedNameInTheDatabase() {
		// Run
		City city = unitUnderTest.create(NAME);
		assertEquals(NAME, city.getName());
		City returned = unitUnderTest.findById(city.getId()).get();
		assertEquals(NAME, returned.getName());
	}

	// @Test
	void createsStoresChangesStoresAndFindsACity() {
		// Run
		City city = unitUnderTest.create(NAME);
		unitUnderTest.changeName(city.getId(), NAME);
		City returned = unitUnderTest.findById(city.getId()).get();
		// Check
		assertEquals(NAME + 1, returned.getName());
	}
}
