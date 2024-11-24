package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
class GenderCommandsTest extends NameProviderCommandsTest<Gender, GenderService, GenderCommands> {

	@Mock
	private GenderService genderService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private GenderCommands unitUnderTest;

	@Override
	GenderService nameProviderService() {
		return genderService;
	}

	@Override
	ExceptionToStringMapper exceptionToStringMapper() {
		return exceptionToStringMapper;
	}

	@Override
	Gender createModel(String name, UUID uuid) {
		return new Gender().setId(uuid).setName(name);
	}

	@Override
	String messageNoData() {
		return GenderCommands.MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return GenderCommands.MESSAGE_NO_GENDER_WITH_ID;
	}

	@Override
	String messageRecordDeleted() {
		return GenderCommands.MESSAGE_GENDER_DELETED;
	}

	@Override
	GenderCommands unitUnderTest() {
		return unitUnderTest;
	}

}
