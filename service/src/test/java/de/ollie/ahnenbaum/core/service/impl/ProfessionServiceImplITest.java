package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.ahnenbaum.core.exception.UniqueConstraintViolationException;
import de.ollie.ahnenbaum.core.model.Profession;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProfessionServiceImplITest {

	private static final String NAME = "name";

	@Inject
	private ProfessionServiceImpl unitUnderTest;

	@AfterEach
	void afterEach() {
		unitUnderTest.findAll().forEach(c -> unitUnderTest.deleteById(c.getId()));
	}

	@Test
	void createsANewProfessionWithPassedNameInTheDatabase() {
		Profession profession = unitUnderTest.create(NAME);
		assertEquals(NAME, profession.getName());
		Profession returned = unitUnderTest.findById(profession.getId()).get();
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
		Profession profession = unitUnderTest.create(NAME);
		unitUnderTest.changeName(profession.getId(), NAME);
		Profession returned = unitUnderTest.findById(profession.getId()).get();
		// Check
		assertEquals(NAME + 1, returned.getName());
	}

	// @Test
	void isAbleToFindAllRecords() {
		// Prepare
		Profession profession0 = unitUnderTest.create(NAME + 0);
		Profession profession1 = unitUnderTest.create(NAME + 1);
		// Run
		List<Profession> returned = unitUnderTest.findAll();
		// Check
		assertEquals(
			List.of(profession0, profession1),
			returned.stream().sorted((p0, p1) -> p0.getName().compareTo(p1.getName())).toList()
		);
	}

	// @Test
	void deleteRemovesAnExistingRecord() {
		// Prepare
		Profession profession0 = unitUnderTest.create(NAME + 0);
		Profession profession1 = unitUnderTest.create(NAME + 1);
		// Run
		unitUnderTest.deleteById(profession0.getId());
		// Check
		assertEquals(profession1, unitUnderTest.findAll().get(0));
	}
}
