package de.ollie.ahnenbaum.backup;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.BackupParameters;
import de.ollie.ahnenbaum.core.service.port.BackupPort;
import jakarta.inject.Named;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class FileBackupAdapter implements BackupPort {

	static final String IOEXCEPTION_MESSAGE_ID = "file.backup.adapter.exception.error-while-writing-backup-file.message";
	static final String MESSAGE = "error while writing the backup file";

	private BackupFileWriter backupFileWriter;
	private DatabaseToJsonMapper databaseToJsonMapper;

	@Override
	public void backup(BackupParameters backupParameters) {
		ensure(backupParameters != null, "backup parameters cannot be null!");
		ensure(
			backupParameters.findParameter(BackupFileWriter.FILE_NAME_PARAMETER).isPresent(),
			new IllegalStateException(BackupFileWriter.FILE_NAME_PARAMETER + " must be set in backup parameters!")
		);
		String fileName = backupParameters
			.findParameter(BackupFileWriter.FILE_NAME_PARAMETER)
			.map(Object::toString)
			.orElse("");
		try {
			backupFileWriter.write(fileName, databaseToJsonMapper.map());
		} catch (IOException ioe) {
			throw new ServiceException(MESSAGE, ioe, IOEXCEPTION_MESSAGE_ID);
		}
	}

	@Override
	public void restore(BackupParameters backupParameters) {
		// TODO Auto-generated method stub

	}
}
