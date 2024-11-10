package de.ollie.ahnenbaum.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.model.Localization;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResourceFileAdapterTest {

	private static final String PROPERTY_ID = "property-id";
	private static final String PROPERTY_VALUE = "property-value";

	@Mock
	private InputStream inputStream;

	@Mock
	private Properties properties;

	@Mock
	private PropertiesFactory propertiesFactory;

	@Mock
	private ResourceFileAdapterConfiguration configuration;

	@Mock
	private ResourceLoader resourceLoader;

	@InjectMocks
	private ResourceFileAdapter unitUnderTest;

	@Nested
	class TestsOfMethodPostConstruct {

		@Test
		void throwsAnException_whenFileIsNotFound_specifiedByClassPath() {
			// Prepare
			when(configuration.getResourceFileName(Localization.EN)).thenReturn("classpath:no/existing/file.name");
			// Run & Check
			assertThrows(IllegalStateException.class, () -> unitUnderTest.postConstruct());
		}

		@Test
		void readsResourcesToMemory_whenFileIsNotFound_specifiedByClassPath() {
			// Prepare
			String resourceName = "src/main/resources/localization/ahnenbaum_resources_en.properties";
			when(configuration.getResourceFileName(Localization.EN)).thenReturn("classpath:" + resourceName);
			when(propertiesFactory.create()).thenReturn(properties);
			when(resourceLoader.getInputStreamFromClassLoader(resourceName)).thenReturn(inputStream);
			when(properties.getProperty(PROPERTY_ID)).thenReturn(PROPERTY_VALUE);
			// Run
			unitUnderTest.postConstruct();
			// Check
			assertEquals(PROPERTY_VALUE, unitUnderTest.getResourceById(PROPERTY_ID, Localization.EN));
		}
	}
}
