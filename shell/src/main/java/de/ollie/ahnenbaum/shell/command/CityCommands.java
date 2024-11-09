package de.ollie.ahnenbaum.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (09.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class CityCommands {

	static final String MESSAGE_NO_DATA = "No cities stored!";
	static final String MESSAGE_NAME_ALREADY_EXISTING = "City with passed name is already existing!";

	private final CityService cityService;

	@ShellMethod(value = "Adds a new city.", key = { "add-city", "ac" })
	public String add(@ShellOption(help = "Name of the city.", value = "name") String name) {
		try {
			return cityService.create(name).toString();
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}

	@ShellMethod(value = "Lists all cities.", key = { "list-cities", "lc" })
	public String list() {
		return cityService.findAll().stream().map(City::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(MESSAGE_NO_DATA);
	}
}
