package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.City;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CityServiceImplITest {

	private static final String NAME = "name";

	@Inject
	private CityServiceImpl unitUnderTest;

	@AfterEach
	void afterEach() {
		unitUnderTest.findAll().forEach(c -> unitUnderTest.deleteById(c.getId()));
	}

	@Test
	void createsANewCityWithPassedNameInTheDatabase() {
		City city = unitUnderTest.create(NAME);
		assertEquals(NAME, city.getName());
		City returned = unitUnderTest.findById(city.getId()).get();
		assertEquals(NAME, returned.getName());
	}

	@Test
	void throwsAnException_callTheMethodWithTheSameNameAgain() {
		unitUnderTest.create(NAME);
		assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(NAME));
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

	// @Test
	void isAbleToFindAllRecords() {
		// Prepare
		City city0 = unitUnderTest.create(NAME + 0);
		City city1 = unitUnderTest.create(NAME + 1);
		// Run
		List<City> returned = unitUnderTest.findAll();
		// Check
		assertEquals(
			List.of(city0, city1),
			returned.stream().sorted((c0, c1) -> c0.getName().compareTo(c1.getName())).toList()
		);
	}

	// @Test
	void deleteRemovesAnExistingRecord() {
		// Prepare
		City city0 = unitUnderTest.create(NAME + 0);
		City city1 = unitUnderTest.create(NAME + 1);
		// Run
		unitUnderTest.deleteById(city0.getId());
		// Check
		assertEquals(city1, unitUnderTest.findAll().get(0));
	}
}
