package de.ollie.ahnenbaum.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

/**
 * @author ollie (09.11.2024)
 */
@ShellComponent
public class GenderCommands extends NameProviderCommands<Gender, GenderService> {

	static final String MESSAGE_GENDER_DELETED = "Gender with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Gender with passed name is already existing!";
	static final String MESSAGE_NO_GENDER_WITH_ID = "No gender stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No genders stored!";

	public GenderCommands(GenderService genderService, ExceptionToStringMapper exceptionToStringMapper) {
		super(genderService, exceptionToStringMapper);
	}

	@Override
	@ShellMethod(value = "Adds a new gender.", key = { "add-gender", "ag" })
	public String add(@ShellOption(help = "Name of the gender.", value = "name") String name) {
		return super.add(name);
	}

	@Override
	@ShellMethod(value = "Change a genders name.", key = { "change-gender-name", "cgn" })
	public String changeName(
			@ShellOption(help = "The id of the gender whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the gender.", value = "newName") String newName) {
		return super.changeName(id, newName);
	}

	@Override
	@ShellMethod(value = "Deletes a gender.", key = { "delete-gender", "dg" })
	public String delete(@ShellOption(help = "Id of the gender to delete.", value = "id") String id) {
		return super.delete(id);
	}

	@Override
	@ShellMethod(value = "Finds a gender by id.", key = { "find-gender", "fg" })
	public String findById(@ShellOption(help = "Id of the gender.", value = "id") String id) {
		return super.findById(id);
	}

	@Override
	@ShellMethod(value = "Lists all genders.", key = { "list-genders", "lg" })
	public String list() {
		return super.list();
	}

	@Override
	Gender setName(Gender model, String name) {
		return model.setName(name);
	}

	@Override
	String modelClassName() {
		return Gender.class.getSimpleName();
	}

	@Override
	String messageRecordDeleted() {
		return MESSAGE_GENDER_DELETED;
	}

	@Override
	String messageNoData() {
		return MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return MESSAGE_NO_GENDER_WITH_ID;
	}

}
