package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.NameProvider;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

abstract class NameProviderServiceImplITest<M extends NameProvider, S extends NameProviderServiceImpl<M>> {

	private static final String NAME = "name";

	abstract S unitUnderTest();

	abstract M setName(M model, String name);

	@AfterEach
	void afterEach() {
		unitUnderTest().findAll().forEach(c -> unitUnderTest().deleteById(c.getId()));
	}

	@Test
	void createsANewGenderWithPassedName_storesItInTheDatabase() {
		M model = unitUnderTest().create(NAME);
		assertEquals(NAME, model.getName());
		assertEquals(NAME, unitUnderTest().findById(model.getId()).get().getName());
	}

	@Test
	void throwsAnException_callingTheMethodWithAnAlreadyExistingNameAgain() {
		unitUnderTest().create(NAME);
		assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest().create(NAME));
	}

	@Test
	void isAbleToFindAllRecords() {
		// Prepare
		M model0 = unitUnderTest().create(NAME + 0);
		M model1 = unitUnderTest().create(NAME + 1);
		// Run
		List<M> returned = unitUnderTest().findAll();
		// Check
		assertEquals(
			List.of(model0, model1),
			returned.stream().sorted((c0, c1) -> c0.getName().compareTo(c1.getName())).toList()
		);
	}

	@Test
	void deleteRemovesAnExistingRecord() {
		// Prepare
		M model0 = unitUnderTest().create(NAME + 0);
		M model1 = unitUnderTest().create(NAME + 1);
		// Run
		unitUnderTest().deleteById(model0.getId());
		// Check
		assertEquals(model1, unitUnderTest().findAll().get(0));
	}

	@Test
	void throwsAnException_whenOptimisticLockingIsRaised() {
		M model0 = unitUnderTest().create(NAME);
		unitUnderTest().update(setName(model0, NAME + 1));
		assertThrows(OptimisticLockingFailureException.class, () -> unitUnderTest().update(setName(model0, NAME + 2)));
	}
}
