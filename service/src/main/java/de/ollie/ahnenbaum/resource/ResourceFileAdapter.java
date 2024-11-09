package de.ollie.ahnenbaum.resource;

import de.ollie.ahnenbaum.core.model.Localization;
import de.ollie.ahnenbaum.core.service.port.ResourcePort;
import jakarta.inject.Named;

@Named
public class ResourceFileAdapter implements ResourcePort {

	@Override
	public String getResourceById(String resourceId, Localization localization) {
		// TODO Add implementation similar to the
		// de.ollie.carp.maps.ws.core.service.impl.localization.FileBasedResourceManagerImpl
		return resourceId;
	}
}
