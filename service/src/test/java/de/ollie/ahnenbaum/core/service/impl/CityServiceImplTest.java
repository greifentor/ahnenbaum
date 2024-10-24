package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

	private static final String NAME = "name";

	@InjectMocks
	private CityServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_changeName_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(ServiceException.class, () -> unitUnderTest.changeName(null));
		}
	}
}
