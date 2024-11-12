package de.ollie.ahnenbaum.core.exception;

public class ParameterIsNullException extends ServiceException {

	public static final String MESSAGE = "parameter cannot be null!";
	public static final String MESSAGE_ID = "parameter-is-null-exception";
	public static final String PARAMETER_ID_ATTRIBUTE_NAME = "idAttributeName";
	public static final String PARAMETER_ENTITY_NAME = "entityName";
	public static final String PARAMETER_VALUE_NAME = "value";

	public ParameterIsNullException(String value, String entityName, String idAttributeName) {
		super(
			MESSAGE,
			null,
			MESSAGE_ID,
			PARAMETER_ENTITY_NAME,
			entityName,
			PARAMETER_ID_ATTRIBUTE_NAME,
			idAttributeName,
			PARAMETER_VALUE_NAME,
			value
		);
	}
}
