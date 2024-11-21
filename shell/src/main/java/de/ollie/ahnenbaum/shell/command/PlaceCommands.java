package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (09.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class PlaceCommands {

	static final String MESSAGE_PLACE_DELETED = "Place with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Place with passed name is already existing!";
	static final String MESSAGE_NO_PLACE_WITH_ID = "No place stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No places stored!";

	private final PlaceService placeService;
	private final ExceptionToStringMapper exceptionToStringMapper;

	@ShellMethod(value = "Adds a new place.", key = { "add-place", "ap" })
	public String add(@ShellOption(help = "Name of the place.", value = "name") String name) {
		try {
			return placeService.create(name).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Change a places name.", key = { "change-place-name", "cpn" })
	public String changeName(
			@ShellOption(help = "The id of the place whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the place.", value = "newName") String newName) {
		try {
			return placeService.changeName(UUID.fromString(id), newName).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Deletes a place.", key = { "delete-place", "dp" })
	public String delete(@ShellOption(help = "Id of the place to delete.", value = "id") String id) {
		try {
			placeService.deleteById(UUID.fromString(id));
			return MESSAGE_PLACE_DELETED.replace("{id}", id);
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Finds a place by id.", key = { "find-place", "fp" })
	public String findById(@ShellOption(help = "Id of the place.", value = "id") String id) {
		try {
			return placeService.findById(UUID.fromString(id)).map(Place::toString)
					.orElse(MESSAGE_NO_PLACE_WITH_ID.replace("{id}", id));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all places.", key = { "list-places", "lp" })
	public String list() {
		return placeService.findAll().stream().map(Place::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(MESSAGE_NO_DATA);
	}

}
