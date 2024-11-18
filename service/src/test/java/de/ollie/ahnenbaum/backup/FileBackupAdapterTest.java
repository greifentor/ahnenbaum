package de.ollie.ahnenbaum.backup;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.backup.model.DatabaseBRO;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.BackupParameters;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileBackupAdapterTest {

	private static final String FILE_NAME = "file-name";
	private static final String JSON = "json";

	@Mock
	private BackupFileWriter backupFileWriter;

	@Mock
	private BackupParameters backupParameters;

	@Mock
	private DatabaseBRO database;

	@Mock
	private DatabaseToJsonMapper databaseToJsonMapper;

	@InjectMocks
	private FileBackupAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_backup_BackupParameters {

		@Test
		void throwsAnException_passingANullValueAsBackupParameters() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.backup(null));
		}

		@Test
		void throwsAnException_passingBackupParametersWithNoFileName() {
			// Prepare
			when(backupParameters.findParameter(BackupFileWriter.FILE_NAME_PARAMETER)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(IllegalStateException.class, () -> unitUnderTest.backup(backupParameters));
		}

		@Test
		void throwsAServiceException_withCorrectCause_whenSomethingGoesWrongWhileWritingTheBackupFile() throws Exception {
			// Prepare
			IOException ioe = trainMock_forException();
			// Run & Check
			ServiceException e = assertThrows(ServiceException.class, () -> unitUnderTest.backup(backupParameters));
			assertSame(ioe, e.getCause());
		}

		private IOException trainMock_forException() throws Exception {
			IOException ioe = new IOException();
			when(backupParameters.findParameter(BackupFileWriter.FILE_NAME_PARAMETER)).thenReturn(Optional.of(FILE_NAME));
			when(databaseToJsonMapper.map()).thenReturn(JSON);
			doThrow(ioe).when(backupFileWriter).write(FILE_NAME, JSON);
			return ioe;
		}

		@Test
		void throwsAServiceException_withCorrectMessage_whenSomethingGoesWrongWhileWritingTheBackupFile() throws Exception {
			// Prepare
			trainMock_forException();
			// Run & Check
			ServiceException e = assertThrows(ServiceException.class, () -> unitUnderTest.backup(backupParameters));
			assertSame(FileBackupAdapter.MESSAGE, e.getMessage());
		}

		@Test
		void throwsAServiceException_withEmptyMessageData_whenSomethingGoesWrongWhileWritingTheBackupFile()
			throws Exception {
			// Prepare
			trainMock_forException();
			// Run & Check
			ServiceException e = assertThrows(ServiceException.class, () -> unitUnderTest.backup(backupParameters));
			assertTrue(e.getMessageData().isEmpty());
		}

		@Test
		void throwsAServiceException_withCorrectMessageId_whenSomethingGoesWrongWhileWritingTheBackupFile()
			throws Exception {
			// Prepare
			trainMock_forException();
			// Run & Check
			ServiceException e = assertThrows(ServiceException.class, () -> unitUnderTest.backup(backupParameters));
			assertSame(FileBackupAdapter.IOEXCEPTION_MESSAGE_ID, e.getMessageId());
		}

		@Test
		void callsTheWritersWriteMethodCorrectly() throws Exception {
			// Prepare
			when(backupParameters.findParameter(BackupFileWriter.FILE_NAME_PARAMETER)).thenReturn(Optional.of(FILE_NAME));
			when(databaseToJsonMapper.map()).thenReturn(JSON);
			// Run
			unitUnderTest.backup(backupParameters);
			// Check
			verify(backupFileWriter, times(1)).write(FILE_NAME, JSON);
		}
	}
}
