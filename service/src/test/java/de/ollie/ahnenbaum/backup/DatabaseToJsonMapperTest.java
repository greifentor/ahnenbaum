package de.ollie.ahnenbaum.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DatabaseToJsonMapperTest {

	@InjectMocks
	private DatabaseToJsonMapper unitUnderTest;

	@Nested
	class TestsOfMethod_map {

		@Test
		void returnsAnEmptyJsonString_passingAnEmptyDatabaseBRO() {
			assertEquals("{}", unitUnderTest.map());
		}
	}
}
