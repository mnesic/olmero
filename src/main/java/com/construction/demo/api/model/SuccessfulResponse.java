/**
 * 
 */
package com.construction.demo.api.model;

public class SuccessfulResponse {

	public static final String CRETAED_OK = "Created successfully.";
	public static final String UPDATED_OK = "Updated successfully.";

	private String info;

	public SuccessfulResponse(final String message) {
		this.info = message;
	}

	public String getResponseInfo() {
		return this.info;
	}

}
