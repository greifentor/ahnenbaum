package de.ollie.ahnenbaum.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.PlaceService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;

/**
 * @author ollie (11.2024)
 */
@ShellComponent
public class PlaceCommands extends NameProviderCommands<Place, PlaceService> {

	static final String MESSAGE_PLACE_DELETED = "Place with id '{id}' is deleted!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "Place with passed name is already existing!";
	static final String MESSAGE_NO_PLACE_WITH_ID = "No place stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No places stored!";

	public PlaceCommands(PlaceService placeService, ExceptionToStringMapper exceptionToStringMapper) {
		super(placeService, exceptionToStringMapper);
	}

	@Override
	@ShellMethod(value = "Adds a new place.", key = { "add-place", "ap" })
	public String add(@ShellOption(help = "Name of the place.", value = "name") String name) {
		return super.add(name);
	}

	@Override
	@ShellMethod(value = "Change a places name.", key = { "change-place-name", "cpn" })
	public String changeName(
			@ShellOption(help = "The id of the place whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the place.", value = "newName") String newName) {
		return super.changeName(id, newName);
	}

	@Override
	@ShellMethod(value = "Deletes a place.", key = { "delete-place", "dp" })
	public String delete(@ShellOption(help = "Id of the place to delete.", value = "id") String id) {
		return super.delete(id);
	}

	@Override
	@ShellMethod(value = "Finds a place by id.", key = { "find-place", "fp" })
	public String findById(@ShellOption(help = "Id of the place.", value = "id") String id) {
		return super.findById(id);
	}

	@Override
	@ShellMethod(value = "Lists all places.", key = { "list-places", "lp" })
	public String list() {
		return super.list();
	}

	@Override
	Place setName(Place model, String name) {
		return model.setName(name);
	}

	@Override
	String modelClassName() {
		return Place.class.getSimpleName();
	}

	@Override
	String messageRecordDeleted() {
		return MESSAGE_PLACE_DELETED;
	}

	@Override
	String messageNoData() {
		return MESSAGE_NO_DATA;
	}

	@Override
	String messageNoRecordWithId() {
		return MESSAGE_NO_PLACE_WITH_ID;
	}

}
