/**
 * 
 */
package com.construction.demo.api.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TenderModel {

	static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	static final String DATE_OUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	private Long id;

	private String name;

	private String projectDescription;

	private LocalDateTime projectDateStart;

	private LocalDateTime projectDateEnd;

	private LocalDateTime tenderDateStart;

	private LocalDateTime tenderDateEnd;

	private Boolean started;

	private List<TenderConditionModel> tenderConditionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonFormat(pattern = DATE_OUT_FORMAT)
	public String getProjectDescription() {
		return projectDescription;
	}

	@JsonFormat(pattern = DATE_FORMAT)
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@JsonFormat(pattern = DATE_OUT_FORMAT)
	public LocalDateTime getProjectDateStart() {
		return projectDateStart;
	}

	@JsonFormat(pattern = DATE_FORMAT)
	public void setProjectDateStart(LocalDateTime projectDateStart) {
		this.projectDateStart = projectDateStart;
	}

	@JsonFormat(pattern = DATE_OUT_FORMAT)
	public LocalDateTime getProjectDateEnd() {
		return projectDateEnd;
	}

	@JsonFormat(pattern = DATE_FORMAT)
	public void setProjectDateEnd(LocalDateTime projectDateEnd) {
		this.projectDateEnd = projectDateEnd;
	}

	@JsonFormat(pattern = DATE_OUT_FORMAT)
	public LocalDateTime getTenderDateStart() {
		return tenderDateStart;
	}

	@JsonFormat(pattern = DATE_FORMAT)
	public void setTenderDateStart(LocalDateTime tenderDateStart) {
		this.tenderDateStart = tenderDateStart;
	}

	@JsonFormat(pattern = DATE_OUT_FORMAT)
	public LocalDateTime getTenderDateEnd() {
		return tenderDateEnd;
	}

	@JsonFormat(pattern = DATE_FORMAT)
	public void setTenderDateEnd(LocalDateTime tenderDateEnd) {
		this.tenderDateEnd = tenderDateEnd;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	@JsonIgnore
	public List<TenderConditionModel> getTenderConditionList() {
		return tenderConditionList;
	}

	public void setTenderConditionList(List<TenderConditionModel> tenderConditionList) {
		this.tenderConditionList = tenderConditionList;
	}

	public static class TenderConditionModel {

		private Long id;

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

}