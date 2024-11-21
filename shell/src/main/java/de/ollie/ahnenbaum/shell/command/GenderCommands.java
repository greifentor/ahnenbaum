package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.GenderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (09.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class GenderCommands {

	static final String MESSAGE_GENDER_DELETED = "Gender with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Gender with passed name is already existing!";
	static final String MESSAGE_NO_GENDER_WITH_ID = "No gender stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No genders stored!";

	private final GenderService genderService;
	private final ExceptionToStringMapper exceptionToStringMapper;

	@ShellMethod(value = "Adds a new gender.", key = { "add-gender", "ag" })
	public String add(@ShellOption(help = "Name of the gender.", value = "name") String name) {
		try {
			return genderService.create(name).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Change a genders name.", key = { "change-gender-name", "cgn" })
	public String changeName(
			@ShellOption(help = "The id of the gender whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the gender.", value = "newName") String newName) {
		try {
			return genderService.changeName(UUID.fromString(id), newName).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Deletes a gender.", key = { "delete-gender", "dg" })
	public String delete(@ShellOption(help = "Id of the gender to delete.", value = "id") String id) {
		try {
			genderService.deleteById(UUID.fromString(id));
			return MESSAGE_GENDER_DELETED.replace("{id}", id);
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Finds a gender by id.", key = { "find-gender", "fg" })
	public String findById(@ShellOption(help = "Id of the gender.", value = "id") String id) {
		try {
			return genderService.findById(UUID.fromString(id)).map(Gender::toString)
					.orElse(MESSAGE_NO_GENDER_WITH_ID.replace("{id}", id));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all genders.", key = { "list-genders", "lg" })
	public String list() {
		return genderService.findAll().stream().map(Gender::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(MESSAGE_NO_DATA);
	}

}
