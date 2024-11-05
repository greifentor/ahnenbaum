package de.ollie.ahnenbaum.backup;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.model.BackupParameters;
import de.ollie.ahnenbaum.core.service.port.BackupPort;
import jakarta.inject.Named;

@Named
public class FileBackupAdapter implements BackupPort {

	@Override
	public void backup(BackupParameters backupParameters) {
		ensure(backupParameters != null, "backup parameters cannot be null!");
	}

	@Override
	public void restore(BackupParameters backupParameters) {
		// TODO Auto-generated method stub

	}
}
