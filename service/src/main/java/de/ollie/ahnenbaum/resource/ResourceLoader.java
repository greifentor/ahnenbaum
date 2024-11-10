package de.ollie.ahnenbaum.resource;

import jakarta.inject.Named;
import java.io.InputStream;

@Named
public class ResourceLoader {

	public InputStream getInputStreamFromClassLoader(String resourceName) {
		return getClass().getClassLoader().getResourceAsStream(resourceName);
	}
}
