package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.BackupParameters;
import de.ollie.ahnenbaum.core.service.BackupService;
import de.ollie.ahnenbaum.core.service.port.BackupPort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BackupServiceImpl implements BackupService {

	private final BackupPort backupPort;

	@Override
	public void backup(BackupParameters backupParameters) {
		try {
			backupPort.backup(backupParameters);
		} catch (Exception e) {
			throw new ServiceException("something went wrong while backup", e, "ServiceException.backup.label");
		}
	}

	@Override
	public void restore(BackupParameters backupParameters) {
		try {
			backupPort.restore(backupParameters);
		} catch (Exception e) {
			throw new ServiceException("something went wrong while restore", e, "ServiceException.restore.label");
		}
	}
}
