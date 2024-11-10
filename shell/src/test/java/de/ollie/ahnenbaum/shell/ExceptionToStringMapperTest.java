package de.ollie.ahnenbaum.shell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Localization;
import de.ollie.ahnenbaum.core.service.port.ResourcePort;

@ExtendWith(MockitoExtension.class)
class ExceptionToStringMapperTest {

	private static final String MESSAGE = "message";
	private static final String MESSAGE_ID = "message-id";
	private static final String MESSAGE_RESOURCE = "message-resource";
	private static final String WILDCARD_0 = "wildcard-0";
	private static final String WILDCARD_1 = "wildcard-1";
	private static final String VALUE_0 = "value-0";
	private static final String VALUE_1 = "value-1";

	@Mock
	private ResourcePort resourceService;

	@InjectMocks
	private ExceptionToStringMapper unitUnderTest;

	@Nested
	class TestsOfMethod_map_ServiceException {

		@Test
		void throwsAnException_passingANullPointer() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.map(null));
		}

		@Test
		void returnsADefaultString_passingAnExceptionWithNoMessageNoCauseNoMessageIdAndNoMessageData() {
			// Prepare
			String expected = ExceptionToStringMapper.DEFAULT_MESSAGE
					.replace(ExceptionToStringMapper.ENTITY_NAME_WILDCARD, ServiceException.class.getSimpleName());
			// Run & Check
			assertEquals(expected, unitUnderTest.map(new ServiceException(null, null, null)));
		}

		@Test
		void returnsAStringWithTheCauseMessage_passingAnExceptionWithNoMessageNoMessageIdAndNoMessageData_butACauseHasAMessage() {
			// Prepare
			String expected = "cause-message";
			// Run & Check
			assertEquals(expected, unitUnderTest.map(new ServiceException(null, new RuntimeException(expected), null)));
		}

		@Test
		void returnsAStringWithTheExceptionMessage_passingAnExceptionWithAMessageACauseWithMessageNoMessageIdAndNoMessageData() {
			assertEquals(MESSAGE,
					unitUnderTest.map(new ServiceException(MESSAGE, new RuntimeException("cause-message"), null)));
		}

		@Test
		void returnsAStringWithTheExceptionMessage_passingAnExceptionWithAMessageACauseWithMessageAMessageIdAndNoMessageData_butMessageIdHasNoResource() {
			// Prepare
			String messageId = ExceptionToStringMapper.RESOURCE_NAME
					.replace(ExceptionToStringMapper.MESSAGE_ID_WILDCARD, MESSAGE_ID);
			when(resourceService.getResourceById(messageId, Localization.EN)).thenReturn(null);
			// Run & Check
			assertEquals(MESSAGE, unitUnderTest
					.map(new ServiceException(MESSAGE, new RuntimeException("cause-message"), MESSAGE_ID)));
		}

		@Test
		void returnsAStringWithTheExceptionMessage_passingAnExceptionWithAMessageACauseWithMessageAMessageIdAndNoMessage_messageIdHasAResource() {
			// Prepare
			String messageId = ExceptionToStringMapper.RESOURCE_NAME
					.replace(ExceptionToStringMapper.MESSAGE_ID_WILDCARD, MESSAGE_ID);
			when(resourceService.getResourceById(messageId, Localization.EN)).thenReturn(MESSAGE_RESOURCE);
			// Run & Check
			assertEquals(MESSAGE_RESOURCE, unitUnderTest
					.map(new ServiceException(MESSAGE, new RuntimeException("cause-message"), MESSAGE_ID)));
		}

		@Test
		void returnsAStringWithTheExceptionMessageAndReplacedWildcards_passingAnExceptionWithAMessageACauseWithMessageAMessageIdAndMessageData() {
			// Prepare
			String messageId = ExceptionToStringMapper.RESOURCE_NAME
					.replace(ExceptionToStringMapper.MESSAGE_ID_WILDCARD, MESSAGE_ID);
			when(resourceService.getResourceById(messageId, Localization.EN))
					.thenReturn("{" + WILDCARD_0 + "}" + MESSAGE_RESOURCE + "{" + WILDCARD_1 + "}");
			// Run & Check
			assertEquals(VALUE_0 + MESSAGE_RESOURCE + VALUE_1, unitUnderTest.map(new ServiceException(MESSAGE,
					new RuntimeException("cause-message"), MESSAGE_ID, WILDCARD_0, VALUE_0, WILDCARD_1, VALUE_1)));
		}

		@Test
		void returnsTheMessage_passingANonServiceException() {
			// Prepare
			Exception e = new Exception(MESSAGE);
			// Run & Check
			assertEquals(e.getClass().getSimpleName() + ": " + MESSAGE, unitUnderTest.map(e));
		}

	}

}
