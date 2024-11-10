package de.ollie.ahnenbaum.resource;

import jakarta.inject.Named;
import java.util.Properties;

@Named
class PropertiesFactory {

	Properties create() {
		return new Properties();
	}
}
