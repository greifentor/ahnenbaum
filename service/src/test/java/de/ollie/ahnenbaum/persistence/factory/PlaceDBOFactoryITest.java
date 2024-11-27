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
class PlaceDBOFactoryITest {

	private static final String NAME = "name";

	@Inject
	private PlaceDBOFactory unitUnderTest;

	@Test
	void returnsAPlaceDBO() {
		assertNotNull(unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewPlaceDBO_onEachCall() {
		assertNotSame(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewDifferentPlaceDBO_onEachCall() {
		assertNotEquals(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}
}
