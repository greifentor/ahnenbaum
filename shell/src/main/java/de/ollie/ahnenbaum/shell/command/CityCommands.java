package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (09.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class CityCommands {

	static final String MESSAGE_NAME_ALREADY_EXISTING = "City with passed name is already existing!";
	static final String MESSAGE_NO_CITY_WITH_ID = "No city stored with id: {id}";
	static final String MESSAGE_NO_DATA = "No cities stored!";

	private final CityService cityService;
	private final ExceptionToStringMapper exceptionToStringMapper;

	@ShellMethod(value = "Adds a new city.", key = { "add-city", "ac" })
	public String add(@ShellOption(help = "Name of the city.", value = "name") String name) {
		try {
			return cityService.create(name).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Change a cities name.", key = { "change-city-name", "ccn" })
	public String changeName(@ShellOption(help = "The id of the city whose name is to change.", value = "id") String id,
			@ShellOption(help = "New name of the city.", value = "newName") String newName) {
		try {
			return cityService.changeName(UUID.fromString(id), newName).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Finds a city by id.", key = { "find-city", "fc" })
	public String findById(@ShellOption(help = "Id of the city.", value = "id") String id) {
		try {
			return cityService.findById(UUID.fromString(id)).map(City::toString)
					.orElse(MESSAGE_NO_CITY_WITH_ID.replace("{id}", id));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all cities.", key = { "list-cities", "lc" })
	public String list() {
		return cityService.findAll().stream().map(City::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(MESSAGE_NO_DATA);
	}
}
