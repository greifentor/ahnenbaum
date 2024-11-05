package de.ollie.ahnenbaum.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BackupParametersTest {

	private static final String NAME = "name";
	private static final Object VALUE = Integer.valueOf(42);

	private BackupParameters unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = new BackupParameters(Map.of());
	}

	@Nested
	class TestsOfConstructor_MapStringObject {

		@Test
		void throwsAnException_passingAsMap() {
			assertThrows(IllegalArgumentException.class, () -> new BackupParameters(null));
		}
	}

	@Nested
	class TestsOfMethod_findParameter_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findParameter(null));
		}

		@Test
		void returnsAnEmptyOptional_whenNoParametersAreStored() {
			assertEquals(Optional.empty(), unitUnderTest.findParameter(NAME));
		}

		@Test
		void returnsAnOptionalWithTheStoredValue_whenAValueIsPresentForPassedName() {
			unitUnderTest = new BackupParameters(Map.of(NAME, VALUE));
			assertEquals(Optional.of(VALUE), unitUnderTest.findParameter(NAME));
		}
	}
}
