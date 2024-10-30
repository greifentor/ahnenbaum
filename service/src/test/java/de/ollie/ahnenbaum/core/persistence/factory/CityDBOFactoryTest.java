package de.ollie.ahnenbaum.core.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.core.service.impl.UUIDServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityDBOFactoryTest {

	private static final String NAME = "name";

	@Spy
	private UUIDService uuidService = new UUIDServiceImpl();

	@InjectMocks
	private CityDBOFactory unitUnderTest;

	@Test
	void returnsACityDBO() {
		assertNotNull(unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewCityDBO_onEachCall() {
		assertNotSame(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}

	@Test
	void returnsANewDifferentCityDBO_onEachCall() {
		assertNotEquals(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
	}
}
