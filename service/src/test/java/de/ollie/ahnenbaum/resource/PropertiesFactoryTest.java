package de.ollie.ahnenbaum.resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PropertiesFactoryTest {

	@InjectMocks
	private PropertiesFactory unitUnderTest;

	@Test
	void returnsAPropertiesObject() {
		assertNotNull(unitUnderTest.create());
	}

	@Test
	void returnsANewPropertiesObjectOnEveryCall() {
		assertNotSame(unitUnderTest.create(), unitUnderTest.create());
	}
}
