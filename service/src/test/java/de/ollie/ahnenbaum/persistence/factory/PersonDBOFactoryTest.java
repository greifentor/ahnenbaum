package de.ollie.ahnenbaum.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.service.UUIDService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonDBOFactoryTest {

	private static final String FIRST_NAME = "first-name";
	private static final String LAST_NAME = "last-name";
	private static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.of(2024, 11, 26, 22, 21);
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private UUIDService uuidService;

	@InjectMocks
	private PersonDBOFactory unitUnderTest;

	@Nested
	class TestsOfMethod_create_String_String_LocalDateTime {

		@Test
		void throwsAnException_passingANullValueAsFirstName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null, LAST_NAME, DATE_OF_BIRTH));
		}

		@Test
		void returnsAPersonDBO_withAnId() {
			when(uuidService.create()).thenReturn(UID);
			assertEquals(UID, unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getId());
		}

		@Test
		void returnsAPersonDBO_withCorrectSetFirstName() {
			assertEquals(FIRST_NAME, unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getFirstName());
		}

		@Test
		void returnsAPersonDBO_withCorrectSetLastName() {
			assertEquals(LAST_NAME, unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getLastName());
		}

		@Test
		void returnsAPersonDBO_withCorrectSetDateOfBirth() {
			assertEquals(DATE_OF_BIRTH, unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getDateOfBirth());
		}
	}
}
