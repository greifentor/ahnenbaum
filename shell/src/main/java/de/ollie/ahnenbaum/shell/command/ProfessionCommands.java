package de.ollie.ahnenbaum.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

/**
 * @author ollie (17.11.2024)
 */
@ShellComponent
public class ProfessionCommands extends NameProviderCommands<Profession, ProfessionService> {

	static final String MESSAGE_PROFESSION_DELETED = "Profession with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Profession with passed name is already existing!";
	static final String MESSAGE_NO_PROFESSION_WITH_ID = "No profession stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No professions stored!";

	public ProfessionCommands(ProfessionService professionService, ExceptionToStringMapper exceptionToStringMapper) {
		super(professionService, exceptionToStringMapper);
	}

	@Override
	@ShellMethod(value = "Adds a new profession.", key = { "add-profession", "apr" })
	public String add(@ShellOption(help = "Name of the profession.", value = "name") String name) {
		return super.add(name);
	}

	@Override
	@ShellMethod(value = "Change a professions name.", key = { "change-profession-name", "cprn" })
	public String changeName(
			@ShellOption(help = "The id of the profession whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the profession.", value = "newName") String newName) {
		return super.changeName(id, newName);
	}

	@Override
	@ShellMethod(value = "Deletes a profession.", key = { "delete-profession", "dpr" })
	public String delete(@ShellOption(help = "Id of the profession to delete.", value = "id") String id) {
		return super.delete(id);
	}

	@Override
	@ShellMethod(value = "Finds a profession by id.", key = { "find-profession", "fpr" })
	public String findById(@ShellOption(help = "Id of the profession.", value = "id") String id) {
		return super.findById(id);
	}

	@Override
	@ShellMethod(value = "Lists all professions.", key = { "list-professions", "lpr" })
	public String list() {
		return super.list();
	}

	@Override
	Profession setName(Profession model, String name) {
		return model.setName(name);
	}

	@Override
	String modelClassName() {
		return Profession.class.getSimpleName();
	}

	@Override
	String messageRecordDeleted() {
		return MESSAGE_PROFESSION_DELETED;
	}

	@Override
	String messageNoData() {
		return MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return MESSAGE_NO_PROFESSION_WITH_ID;
	}

}
