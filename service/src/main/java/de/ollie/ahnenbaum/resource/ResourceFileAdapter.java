package de.ollie.ahnenbaum.resource;

import de.ollie.ahnenbaum.core.model.Localization;
import de.ollie.ahnenbaum.core.service.port.ResourcePort;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ResourceFileAdapter implements ResourcePort {

	private final PropertiesFactory propertiesFactory;
	private final ResourceFileAdapterConfiguration configuration;
	private final ResourceLoader resourceLoader;

	private final Map<Localization, Properties> resources = new HashMap<>();

	@PostConstruct
	void postConstruct() {
		for (Localization localization : Localization.values()) {
			String fileName = configuration.getResourceFileName(localization);
			try {
				Properties properties = propertiesFactory.create();
				properties.load(resourceLoader.getInputStreamFromClassLoader(fileName.replace("classpath:", "")));
				resources.put(localization, properties);
			} catch (Exception e) {
				throw new IllegalStateException(
					"Something went wrong while reading '" +
					fileName +
					"' for localization: " +
					localization +
					". Reason: " +
					e.getMessage()
				);
			}
		}
	}

	@Override
	public String getResourceById(String resourceId, Localization localization) {
		return resources.get(localization).getProperty(resourceId);
	}
}
