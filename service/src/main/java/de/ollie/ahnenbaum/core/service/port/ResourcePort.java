package de.ollie.ahnenbaum.core.service.port;

import de.ollie.ahnenbaum.core.model.Localization;

public interface ResourcePort {
	String getResourceById(String resourceId, Localization localization);
}
