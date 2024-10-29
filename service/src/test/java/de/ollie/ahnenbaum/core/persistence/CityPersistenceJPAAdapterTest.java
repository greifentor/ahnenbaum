package de.ollie.ahnenbaum.core.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.UUIDService;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityPersistenceJPAAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private UUIDService uuidService;

	@InjectMocks
	private CityPersistenceJPAAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_create {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void throwsAnException_passingABlankString() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create("\r\n\t "));
		}

		@Test
		void returnsANewCity_withNewId() {
			// Prepare
			when(uuidService.create()).thenReturn(UID);
			// Run
			City returned = unitUnderTest.create(NAME);
			// Check
			assertEquals(UID, returned.getId());
		}

		@Test
		void returnsANewCity_withPassedName() {
			// Prepare
			when(uuidService.create()).thenReturn(UID);
			// Run
			City returned = unitUnderTest.create(NAME);
			// Check
			assertEquals(NAME, returned.getName());
		}

		@Test
		void returnsANewCity_onEachCall() {
			assertNotSame(unitUnderTest.create(NAME), unitUnderTest.create(NAME));
		}
	}
}
