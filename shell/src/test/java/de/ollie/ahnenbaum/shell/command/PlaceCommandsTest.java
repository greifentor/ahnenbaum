package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
class PlaceCommandsTest extends NameProviderCommandsTest<Place, PlaceService, PlaceCommands> {

	@Mock
	private PlaceService placeService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private PlaceCommands unitUnderTest;

	@Override
	PlaceService nameProviderService() {
		return placeService;
	}

	@Override
	ExceptionToStringMapper exceptionToStringMapper() {
		return exceptionToStringMapper;
	}

	@Override
	Place createModel(String name, UUID uuid) {
		return new Place().setId(uuid).setName(name);
	}

	@Override
	String messageNoData() {
		return PlaceCommands.MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return PlaceCommands.MESSAGE_NO_PLACE_WITH_ID;
	}

	@Override
	String messageRecordDeleted() {
		return PlaceCommands.MESSAGE_PLACE_DELETED;
	}

	@Override
	PlaceCommands unitUnderTest() {
		return unitUnderTest;
	}
}