package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.BackupParameters;
import de.ollie.ahnenbaum.core.service.port.BackupPort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BackupServiceImplTest {

	@Mock
	private BackupParameters backupParameters;

	@Mock
	private BackupPort backupPort;

	@InjectMocks
	private BackupServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_backup_BackupParameters {

		@Test
		void throwsAServiceException_whenBackupPortMethodCallThrowsAnException() {
			doThrow(new RuntimeException()).when(backupPort).backup(null);
			assertThrows(ServiceException.class, () -> unitUnderTest.backup(null));
		}

		@Test
		void callsTheBackupPortsMethodCorrectly() {
			unitUnderTest.backup(backupParameters);
			verify(backupPort, times(1)).backup(backupParameters);
		}
	}

	@Nested
	class TestsOfMethod_restore_BackupParameters {

		@Test
		void throwsAServiceException_whenBackupPortMethodCallThrowsAnException() {
			doThrow(new RuntimeException()).when(backupPort).restore(null);
			assertThrows(ServiceException.class, () -> unitUnderTest.restore(null));
		}

		@Test
		void callsTheBackupPortsMethodCorrectly() {
			unitUnderTest.restore(backupParameters);
			verify(backupPort, times(1)).restore(backupParameters);
		}
	}
}
