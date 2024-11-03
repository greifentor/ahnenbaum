package de.ollie.ahnenbaum.core.service.port;

public interface BackupPort {
	void backup(String backupName);

	void restore(String backupName);
}
