package de.ollie.ahnenbaum.persistence.factory;

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
class PlaceDBOFactoryTest {

	private static final String NAME = "name";

	@Spy
	private UUIDService uuidService = new UUIDServiceImpl();

	@InjectMocks
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
