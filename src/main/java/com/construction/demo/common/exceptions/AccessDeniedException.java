package com.construction.demo.common.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends Exception {

	private HttpStatus statusCode;

	public AccessDeniedException(String messge) {
		super(messge);
	}

	public AccessDeniedException(HttpStatus statusCode, String message) {
		super(message);
		this.setStatusCode(statusCode);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
