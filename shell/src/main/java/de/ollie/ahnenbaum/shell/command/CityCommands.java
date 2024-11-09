package de.ollie.ahnenbaum.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import lombok.RequiredArgsConstructor;

/**
 * @author ollie (09.11.2024)
 */
@RequiredArgsConstructor
@ShellComponent
public class CityCommands {

	static final String NO_DATA_OUTPUT = "No cities stored!";

	private final CityService cityService;

//	@ShellMethod(value = "Lists all cities.", key = { "list-cities", "lc" })
//	public String add() {
//		return cityService.findAll().stream().map(City::toString).reduce((s0, s1) -> s0 + "\n" + s1)
//				.orElse(NO_DATA_OUTPUT);
//	}

	@ShellMethod(value = "Lists all cities.", key = { "list-cities", "lc" })
	public String list() {
		return cityService.findAll().stream().map(City::toString).reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse(NO_DATA_OUTPUT);
	}
}
