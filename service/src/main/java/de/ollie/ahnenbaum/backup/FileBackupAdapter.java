package de.ollie.ahnenbaum.backup;

import de.ollie.ahnenbaum.core.model.BackupParameters;
import de.ollie.ahnenbaum.core.service.port.BackupPort;
import jakarta.inject.Named;

@Named
public class FileBackupAdapter implements BackupPort {

	@Override
	public void backup(BackupParameters backupParameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void restore(BackupParameters backupParameters) {
		// TODO Auto-generated method stub

	}
}
