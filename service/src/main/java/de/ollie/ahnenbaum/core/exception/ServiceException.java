package de.ollie.ahnenbaum.core.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

	private String messageId;
	private List<String> messageData = new ArrayList<>();

	public ServiceException(String message, Throwable cause, String messageId, String... messageData) {
		super(message, cause);
		this.messageId = messageId;
		for (String md : messageData) {
			this.messageData.add(md);
		}
	}
}
