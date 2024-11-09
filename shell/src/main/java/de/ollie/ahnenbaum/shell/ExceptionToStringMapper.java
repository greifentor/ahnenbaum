package de.ollie.ahnenbaum.shell;

import static de.ollie.ahnenbaum.util.Check.ensure;

import java.util.Map.Entry;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.Localization;
import de.ollie.ahnenbaum.core.service.port.ResourcePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ExceptionToStringMapper {

	static final String CLASS_NAME_WILDCARD = "{className}";
	static final String DEFAULT_MESSAGE = CLASS_NAME_WILDCARD + " thrown with unknown reason!";

	private final ResourcePort resourceService;

	public String map(ServiceException exception) {
		ensure(exception != null, "exception cannot be null!");
		String resource = resourceService.getResourceById(exception.getMessageId(), Localization.DE);
		if ((exception.getMessageId() != null) && (resource != null)) {
			for (Entry<String, String> e : exception.getMessageData().entrySet()) {
				resource = resource.replace(e.getKey(), e.getValue());
			}
			return resource;
		}
		if (exception.getMessage() != null) {
			return exception.getMessage();
		} else if (exception.getCause() != null) {
			return exception.getCause().getMessage();
		}
		return DEFAULT_MESSAGE.replace(CLASS_NAME_WILDCARD, exception.getClass().getSimpleName());
	}

}
