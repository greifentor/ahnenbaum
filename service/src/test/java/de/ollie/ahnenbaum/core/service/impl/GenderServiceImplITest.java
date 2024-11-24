package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Gender;
import jakarta.inject.Inject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GenderServiceImplITest extends NameProviderServiceImplITest<Gender, GenderServiceImpl> {

	@Inject
	private GenderServiceImpl unitUnderTest;

	@Override
	GenderServiceImpl unitUnderTest() {
		return unitUnderTest;
	}

	@Override
	Gender setName(Gender model, String name) {
		return model.setName(name);
	}
}
