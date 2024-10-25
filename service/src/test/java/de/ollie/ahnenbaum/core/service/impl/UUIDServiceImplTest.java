package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UUIDServiceImplTest {

	@InjectMocks
	private UUIDServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_create {

		@Test
		void returnsAnUUID() {
			assertNotNull(unitUnderTest.create());
		}

		@Test
		void returnsANewUUIDEveryCall() {
			assertNotEquals(unitUnderTest.create(), unitUnderTest.create());
		}
	}
}
