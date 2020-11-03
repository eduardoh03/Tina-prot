package com.somostina.tina.services.exceptions;

public class InvalidDataAccessApi extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDataAccessApi(String msg) {
		super(msg);
	}

	public InvalidDataAccessApi(String msg, Throwable cause) {
		super(msg, cause);
	}

}
