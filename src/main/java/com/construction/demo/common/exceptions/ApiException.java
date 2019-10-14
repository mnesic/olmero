package com.construction.demo.common.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {

	private HttpStatus statusCode;

	public ApiException(HttpStatus statusCode, String msg) {
		super(msg);
		this.setStatusCode(statusCode);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
