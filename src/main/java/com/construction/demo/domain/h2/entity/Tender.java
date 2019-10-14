/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TENDER")
public class Tender extends BaseEntityAudit {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ISSUER_ID")
	private Issuer issuer;

	@NotNull
	@Column(unique = true, length = 64)
	private String name;

	@Column(name = "PROEJCT_DESCRIPTION", length = 256)
	private String projectDescription;

	@Column(name = "PROJECT_DATE_START")
	private LocalDateTime projectDateStart;

	@Column(name = "PROJECT_DATE_END")
	private LocalDateTime projectDateEnd;

	@Column(name = "tenderDateStart")
	private LocalDateTime tenderDateStart;

	@Column(name = "tenderDateEnd")
	private LocalDateTime tenderDateEnd;

	@NotNull
	@Column
	private Boolean started;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tender")
	private List<TenderCondition> tenderConditionList;

	public Issuer getIssuer() {
		return issuer;
	}

	public void setIssuer(Issuer issuer) {
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

	public List<TenderCondition> getTenderConditionList() {
		return tenderConditionList;
	}

	public void setTenderConditionList(List<TenderCondition> tenderConditionList) {
		if (tenderConditionList != null) {
			tenderConditionList.forEach(tc -> tc.setTender(this));
			this.tenderConditionList = tenderConditionList;
		}
	}

}