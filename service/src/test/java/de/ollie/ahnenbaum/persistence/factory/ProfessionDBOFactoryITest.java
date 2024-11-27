package de.ollie.ahnenbaum.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProfessionDBOFactoryITest {

	private static final String NAME = "name";

	@Inject
	private ProfessionDBOFactory unitUnderTest;

	@Test
	void returnsAProfessionDBO() {
		assertNotNull(unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewProfessionDBO_onEachCall() {
		assertNotSame(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewDifferentProfessionDBO_onEachCall() {
		assertNotEquals(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}
}
