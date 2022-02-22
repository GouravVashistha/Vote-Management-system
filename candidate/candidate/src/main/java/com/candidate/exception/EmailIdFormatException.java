package com.candidate.exception;

public class EmailIdFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailIdFormatException() {

	}

	public EmailIdFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EmailIdFormatException(String message, Throwable cause) {
		super(message, cause);

	}

	public EmailIdFormatException(String message) {
		super(message);

	}

	public EmailIdFormatException(Throwable cause) {
		super(cause);

	}

}
