package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

@ExtendWith(MockitoExtension.class)
public class ProfessionCommandsTest
		extends NameProviderCommandsTest<Profession, ProfessionService, ProfessionCommands> {

	@Mock
	private ProfessionService professionService;

	@Mock
	private ExceptionToStringMapper exceptionToStringMapper;

	@InjectMocks
	private ProfessionCommands unitUnderTest;

	@Override
	ProfessionService nameProviderService() {
		return professionService;
	}

	@Override
	ExceptionToStringMapper exceptionToStringMapper() {
		return exceptionToStringMapper;
	}

	@Override
	Profession createModel(String name, UUID uuid) {
		return new Profession().setId(uuid).setName(name);
	}

	@Override
	String messageNoData() {
		return ProfessionCommands.MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return ProfessionCommands.MESSAGE_NO_PROFESSION_WITH_ID;
	}

	@Override
	String messageRecordDeleted() {
		return ProfessionCommands.MESSAGE_PROFESSION_DELETED;
	}

	@Override
	ProfessionCommands unitUnderTest() {
		return unitUnderTest;
	}
}
