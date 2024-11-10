package de.ollie.ahnenbaum.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.ahnenbaum.core.model.Localization;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ResourceFileAdapterConfigurationTest {

	private static final String FILE_NAME_PATTERN =
		"filename" + ResourceFileAdapterConfiguration.LOCALIZATION_WILDCARD + ".extension";

	@InjectMocks
	private ResourceFileAdapterConfiguration unitUnderTest;

	@Nested
	class TestsOfMethod_getResourceFileName_Localization {

		@Test
		void returnsTheFileNamePattern_withNoLocalization_passingNoLocalization() {
			// Prepare
			String expected = FILE_NAME_PATTERN.replace(ResourceFileAdapterConfiguration.LOCALIZATION_WILDCARD, "");
			ReflectionTestUtils.setField(unitUnderTest, "fileNamePattern", FILE_NAME_PATTERN);
			// Run & Check
			assertEquals(expected, unitUnderTest.getResourceFileName(null));
		}

		@Test
		void returnsTheFileNamePattern_withLocalizationTokenLowerCase_passingALocalization() {
			// Prepare
			String expected = FILE_NAME_PATTERN.replace(ResourceFileAdapterConfiguration.LOCALIZATION_WILDCARD, "_en");
			ReflectionTestUtils.setField(unitUnderTest, "fileNamePattern", FILE_NAME_PATTERN);
			// Run & Check
			assertEquals(expected, unitUnderTest.getResourceFileName(Localization.EN));
		}
	}
}
