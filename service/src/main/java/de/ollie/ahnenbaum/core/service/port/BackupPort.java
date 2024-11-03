package de.ollie.ahnenbaum.core.service.port;

import de.ollie.ahnenbaum.core.model.BackupParameters;

public interface BackupPort {
	void backup(BackupParameters backupParameters);

	void restore(BackupParameters backupParameters);
}
