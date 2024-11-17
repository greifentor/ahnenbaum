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
public class ProfessionDBOFactoryTest {

	private static final String NAME = "name";

	@Spy
	private UUIDService uuidService = new UUIDServiceImpl();

	@InjectMocks
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
