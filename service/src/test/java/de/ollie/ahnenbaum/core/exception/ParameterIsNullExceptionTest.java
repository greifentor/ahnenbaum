package de.ollie.ahnenbaum.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParameterIsNullExceptionTest {

	private static final String ENTITY_NAME = "Entity-Name";
	private static final String ID_ATTRIBUTE_NAME = "Id-Attribute-Name";
	private static final String VALUE = "Value";

	private ParameterIsNullException unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = new ParameterIsNullException(VALUE, ENTITY_NAME, ID_ATTRIBUTE_NAME);
	}

	@Nested
	class TestsOfConstructor_string_Throwable_String_String {

		@Test
		void setsTheCauseCorrectly() {
			assertNull(unitUnderTest.getCause());
		}

		@Test
		void setsTheMessageCorrectly() {
			assertEquals(ParameterIsNullException.MESSAGE, unitUnderTest.getMessage());
		}

		@Test
		void setsTheMessageDataCorrectly_withColumnNames() {
			assertEquals(
				Map.of(
					ParameterIsNullException.PARAMETER_ENTITY_NAME,
					ENTITY_NAME,
					ParameterIsNullException.PARAMETER_ID_ATTRIBUTE_NAME,
					ID_ATTRIBUTE_NAME,
					ParameterIsNullException.PARAMETER_VALUE_NAME,
					VALUE
				),
				unitUnderTest.getMessageData()
			);
		}

		@Test
		void setsTheMessageDataCorrectly_withOneColumnNameOnly() {
			assertEquals(
				Map.of(
					ParameterIsNullException.PARAMETER_ENTITY_NAME,
					ENTITY_NAME,
					ParameterIsNullException.PARAMETER_ID_ATTRIBUTE_NAME,
					ID_ATTRIBUTE_NAME,
					ParameterIsNullException.PARAMETER_VALUE_NAME,
					VALUE
				),
				unitUnderTest.getMessageData()
			);
		}

		@Test
		void setsTheMessageIdCorrectly() {
			assertEquals(ParameterIsNullException.MESSAGE_ID, unitUnderTest.getMessageId());
		}
	}
}
