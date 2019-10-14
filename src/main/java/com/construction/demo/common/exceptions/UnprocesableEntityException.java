package com.construction.demo.common.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.FieldError;

public class UnprocesableEntityException extends Exception {

	private List<FieldError> fieldErrors;

	public UnprocesableEntityException(FieldError errorField) {
		this.fieldErrors = Arrays.asList(errorField);
	}

	public UnprocesableEntityException(FieldError fieldError, Throwable e) {
		super(e);
		this.fieldErrors = Arrays.asList(fieldError);
	}

	public UnprocesableEntityException(List<FieldError> fieldErrors, Throwable e) {
		super(e);
		this.fieldErrors = fieldErrors;
	}

	public void addFieldError(FieldError fieldError) {
		fieldErrors.add(fieldError);
	}

}
