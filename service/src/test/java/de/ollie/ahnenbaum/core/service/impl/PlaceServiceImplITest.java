package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Place;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlaceServiceImplITest {

	private static final String NAME = "name";

	@Inject
	private PlaceServiceImpl unitUnderTest;

	@AfterEach
	void afterEach() {
		unitUnderTest.findAll().forEach(c -> unitUnderTest.deleteById(c.getId()));
	}

	@Test
	void createsANewPlaceWithPassedName_storesItInTheDatabase() {
		Place model = unitUnderTest.create(NAME);
		assertEquals(NAME, model.getName());
		assertEquals(NAME, unitUnderTest.findById(model.getId()).get().getName());
	}

	@Test
	void throwsAnException_callTheMethodWithTheSameNameAgain() {
		unitUnderTest.create(NAME);
		assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(NAME));
	}

	@Test
	void isAbleToFindAllRecords() {
		// Prepare
		Place model0 = unitUnderTest.create(NAME + 0);
		Place model1 = unitUnderTest.create(NAME + 1);
		// Run
		List<Place> returned = unitUnderTest.findAll();
		// Check
		assertEquals(
			List.of(model0, model1),
			returned.stream().sorted((c0, c1) -> c0.getName().compareTo(c1.getName())).toList()
		);
	}

	@Test
	void deleteRemovesAnExistingRecord() {
		// Prepare
		Place model0 = unitUnderTest.create(NAME + 0);
		Place model1 = unitUnderTest.create(NAME + 1);
		// Run
		unitUnderTest.deleteById(model0.getId());
		// Check
		assertEquals(model1, unitUnderTest.findAll().get(0));
	}

	@Test
	void throwsAnException_whenOptimisticLockingIsRaised() {
		Place model0 = unitUnderTest.create(NAME);
		unitUnderTest.update(model0.setName(NAME + 1));
		assertThrows(OptimisticLockingFailureException.class, () -> unitUnderTest.update(model0.setName(NAME + 2)));
	}
}
