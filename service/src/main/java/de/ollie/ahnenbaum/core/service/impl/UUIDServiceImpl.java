package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.service.UUIDService;
import java.util.UUID;

public class UUIDServiceImpl implements UUIDService {

	@Override
	public UUID create() {
		return UUID.randomUUID();
	}
}
