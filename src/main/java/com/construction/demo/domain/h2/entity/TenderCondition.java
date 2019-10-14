/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TENDER_CONDITION")
public class TenderCondition extends BaseEntityAudit {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TENDER_ID")
	private Tender tender;

	@NotNull
	@Column(length = 64)
	private String name;

	@Column(length = 256)
	private String request;

	@Column(name = "REQUEST_INFO", length = 256)
	private String requestInfo;

	@Column(length = 512)
	private String description;

	@NotNull
	private Boolean obligatory;

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
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