package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6832152616631408090L;

	public DuplicateEntryException(String message) {
		super(message);
	}

}
