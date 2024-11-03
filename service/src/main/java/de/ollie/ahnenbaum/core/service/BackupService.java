package de.ollie.ahnenbaum.core.service;

import de.ollie.ahnenbaum.core.model.BackupParameters;

public interface BackupService {
	void backup(BackupParameters backupParameters);

	void restore(BackupParameters backupParameters);
}
