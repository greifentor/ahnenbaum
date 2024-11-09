package de.ollie.ahnenbaum.shell;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import jakarta.inject.Named;

@Named
public class ExceptionToStringMapper {

	public String map(ServiceException exception) {
		ensure(exception != null, "exception cannot be null!");
		return null;
	}

}
