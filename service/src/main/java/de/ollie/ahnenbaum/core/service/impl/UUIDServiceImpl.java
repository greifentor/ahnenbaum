package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.service.UUIDService;
import jakarta.inject.Named;
import java.util.UUID;

@Named
class UUIDServiceImpl implements UUIDService {

	@Override
	public UUID create() {
		return UUID.randomUUID();
	}
}
