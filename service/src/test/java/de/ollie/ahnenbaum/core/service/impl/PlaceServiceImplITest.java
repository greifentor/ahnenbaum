package de.ollie.ahnenbaum.core.service.impl;

import de.ollie.ahnenbaum.core.model.Place;
import jakarta.inject.Inject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlaceServiceImplITest extends NameProviderServiceImplITest<Place, PlaceServiceImpl> {

	@Inject
	private PlaceServiceImpl unitUnderTest;

	@Override
	PlaceServiceImpl unitUnderTest() {
		return unitUnderTest;
	}

	@Override
	Place setName(Place model, String name) {
		return model.setName(name);
	}
}
