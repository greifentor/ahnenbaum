package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Profession;
import jakarta.inject.Inject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProfessionServiceImplITest extends NameProviderServiceImplITest<Profession, ProfessionServiceImpl> {

	@Inject
	private ProfessionServiceImpl unitUnderTest;

	@Override
	ProfessionServiceImpl unitUnderTest() {
		return unitUnderTest;
	}

	@Override
	Profession setName(Profession model, String name) {
		return model.setName(name);
	}
}
