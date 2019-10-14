/**
 * 
 */
package com.construction.demo.services.dto;

public class TenderConditionDto {

	private Long id;

	private TenderDto tender;

	private String name;

	private String request;

	private String requestInfo;

	private String description;

	private Boolean obligatory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TenderDto getTender() {
		return tender;
	}

	public void setTender(TenderDto tender) {
		this.tender = tender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getObligatory() {
		return obligatory;
	}

	public void setObligatory(Boolean obligatory) {
		this.obligatory = obligatory;
	}

}