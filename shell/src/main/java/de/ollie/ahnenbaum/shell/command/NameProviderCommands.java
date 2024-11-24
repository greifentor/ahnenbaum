package de.ollie.ahnenbaum.shell.command;

import java.util.UUID;

import de.ollie.ahnenbaum.core.exception.NoSuchRecordException;
import de.ollie.ahnenbaum.core.model.NameProvider;
import de.ollie.ahnenbaum.core.service.NameProviderService;
import de.ollie.ahnenbaum.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NameProviderCommands<M extends NameProvider, S extends NameProviderService<M>> {

	private final S service;
	private final ExceptionToStringMapper exceptionToStringMapper;

	abstract M setName(M model, String name);

	abstract String modelClassName();

	abstract String messageRecordDeleted();

	abstract String messageNoData();

	abstract String messageNoRecordWithId();

	protected String add(String name) {
		try {
			return service.create(name).toString();
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	protected String changeName(String id, String newName) {
		try {
			return service.findById(UUID.fromString(id)).map(m -> service.update(setName(m, newName)).toString())
					.orElseThrow(() -> new NoSuchRecordException(id, modelClassName(), "id"));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	protected String delete(String id) {
		try {
			service.deleteById(UUID.fromString(id));
			return messageRecordDeleted().replace("{id}", id);
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	protected String findById(String id) {
		try {
			return service.findById(UUID.fromString(id)).map(m -> "" + m)
					.orElse(messageNoRecordWithId().replace("{id}", id));
		} catch (Exception e) {
			return exceptionToStringMapper.map(e);
		}
	}

	protected String list() {
		return service.findAll().stream().map(m -> "" + m).reduce((s0, s1) -> s0 + "\n" + s1).orElse(messageNoData());
	}

}
