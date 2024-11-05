package de.ollie.ahnenbaum.core.model;

import static de.ollie.ahnenbaum.util.Check.ensure;

import java.util.Map;
import java.util.Optional;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class BackupParameters {

	private final Map<String, Object> values;

	public BackupParameters(Map<String, Object> values) {
		ensure(values != null, "values cannot be null!");
		this.values = values;
	}

	public Optional<Object> findParameter(String name) {
		ensure(name != null, "name cannot be null");
		return Optional.ofNullable(values.get(name));
	}
}
