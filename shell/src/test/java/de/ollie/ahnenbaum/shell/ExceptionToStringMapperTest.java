package de.ollie.ahnenbaum.shell;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExceptionToStringMapperTest {

	@InjectMocks
	private ExceptionToStringMapper unitUnderTest;

	@Nested
	class TestsOfMethod_map_ServiceException {

		@Test
		void throwsAnException_passingANullPointer() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.map(null));
		}

	}

}
