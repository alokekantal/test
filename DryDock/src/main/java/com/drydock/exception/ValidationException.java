package com.drydock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ValidationException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -4138398133645968080L;

	public ValidationException(String exception) {
	    super(exception);
	  }

	}
