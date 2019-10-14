/**
 * 
 */
package com.construction.demo.services.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TenderDto {

	private Long id;

	private IssuerDto issuer;

	private String name;

	private String projectDescription;

	private LocalDateTime projectDateStart;

	private LocalDateTime projectDateEnd;

	private LocalDateTime tenderDateStart;

	private LocalDateTime tenderDateEnd;

	private Boolean started;

	private Boolean deleted;

	private List<TenderConditionDto> tenderConditionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IssuerDto getIssuer() {
		return issuer;
	}

	public void setIssuer(IssuerDto issuer) {
		this.issuer = issuer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public LocalDateTime getProjectDateStart() {
		return projectDateStart;
	}

	public void setProjectDateStart(LocalDateTime projectDateStart) {
		this.projectDateStart = projectDateStart;
	}

	public LocalDateTime getProjectDateEnd() {
		return projectDateEnd;
	}

	public void setProjectDateEnd(LocalDateTime projectDateEnd) {
		this.projectDateEnd = projectDateEnd;
	}

	public LocalDateTime getTenderDateStart() {
		return tenderDateStart;
	}

	public void setTenderDateStart(LocalDateTime tenderDateStart) {
		this.tenderDateStart = tenderDateStart;
	}

	public LocalDateTime getTenderDateEnd() {
		return tenderDateEnd;
	}

	public void setTenderDateEnd(LocalDateTime tenderDateEnd) {
		this.tenderDateEnd = tenderDateEnd;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<TenderConditionDto> getTenderConditionList() {
		return tenderConditionList;
	}

	public void setTenderConditionList(List<TenderConditionDto> tenderConditionList) {
		this.tenderConditionList = tenderConditionList;
	}

}