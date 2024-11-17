package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.ProfessionService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (17.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class ProfessionCommands {

	static final String MESSAGE_PROFESSION_DELETED = "Profession with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Profession with passed name is already existing!";
	static final String MESSAGE_NO_PROFESSION_WITH_ID = "No profession stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No professions stored!";

	private final ProfessionService professionService;
	private final ExceptionToStringMapper exceptionToStringMapper;

	@ShellMethod(value = "Adds a new profession.", key = { "add-profession", "apr" })
	public String add(@ShellOption(help = "Name of the profession.", value = "name") String name) {
		try {
			return professionService.create(name).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Change a professions name.", key = { "change-profession-name", "cprn" })
	public String changeName(
			@ShellOption(help = "The id of the profession whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the profession.", value = "newName") String newName) {
		try {
			return professionService.changeName(UUID.fromString(id), newName).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Deletes a profession.", key = { "delete-profession", "dpr" })
	public String delete(@ShellOption(help = "Id of the profession to delete.", value = "id") String id) {
		try {
			professionService.deleteById(UUID.fromString(id));
			return MESSAGE_PROFESSION_DELETED.replace("{id}", id);
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Finds a profession by id.", key = { "find-profession", "fpr" })
	public String findById(@ShellOption(help = "Id of the profession.", value = "id") String id) {
		try {
			return professionService.findById(UUID.fromString(id)).map(Profession::toString)
					.orElse(MESSAGE_NO_PROFESSION_WITH_ID.replace("{id}", id));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all professions.", key = { "list-professions", "lpr" })
	public String list() {
		return professionService.findAll().stream().map(Profession::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(MESSAGE_NO_DATA);
	}

}
