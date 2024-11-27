package de.ollie.ahnenbaum.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import jakarta.inject.Inject;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonDBOFactoryITest {

	private static final String FIRST_NAME = "first-name";
	private static final String LAST_NAME = "last-name";
	private static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.of(2024, 11, 26, 22, 21);

	@Inject
	private PersonDBOFactory unitUnderTest;

	@Nested
	class TestsOfMethod_create_String_String_LocalDateTime {

		@Test
		void returnsAnObjectWithADifferentUUIDOnEachCall() {
			assertNotEquals(
				unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getId(),
				unitUnderTest.create(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH).getId()
			);
		}
	}
}
