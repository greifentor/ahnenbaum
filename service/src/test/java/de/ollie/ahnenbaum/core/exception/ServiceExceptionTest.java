package de.ollie.ahnenbaum.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ServiceExceptionTest {

	private static final String MESSAGE = "message";
	private static final String MESSAGE_ID = "message-id";
	private static final String MESSAGE_DATA_0 = "message-data-0";
	private static final String MESSAGE_DATA_1 = "message-data-1";
	private static final Throwable THROWABLE = new RuntimeException();

	private ServiceException unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = new ServiceException(MESSAGE, THROWABLE, MESSAGE_ID, MESSAGE_DATA_0, MESSAGE_DATA_1);
	}

	@Nested
	class TestsOfConstructor_string_Throwable_String_StringVarArg {

		@Test
		void setsTheCauseCorrectly() {
			assertSame(THROWABLE, unitUnderTest.getCause());
		}

		@Test
		void setsTheMessageCorrectly() {
			assertEquals(MESSAGE, unitUnderTest.getMessage());
		}

		@Test
		void setsTheMessageDataCorrectly() {
			assertEquals(List.of(MESSAGE_DATA_0, MESSAGE_DATA_1), unitUnderTest.getMessageData());
		}

		@Test
		void setsTheMessageIdCorrectly() {
			assertEquals(MESSAGE_ID, unitUnderTest.getMessageId());
		}
	}
}
