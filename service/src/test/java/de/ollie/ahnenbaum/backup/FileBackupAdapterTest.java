package de.ollie.ahnenbaum.backup;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileBackupAdapterTest {

	@InjectMocks
	private FileBackupAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_backup_BackupParameters {

		@Test
		void throwsAnException_passingANullValueAsBackupParameters() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.backup(null));
		}
	}
}
