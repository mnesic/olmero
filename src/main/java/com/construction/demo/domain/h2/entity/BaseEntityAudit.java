package com.construction.demo.domain.h2.entity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATE_CREATED")
	private LocalDateTime dateCreated;

	@Column(name = "DATE_MODIFIED")
	private LocalDateTime dateModified;

	@Column
	private Boolean deleted;

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets dateCreated & deleted before insert
	 */
	@PrePersist
	public void setCreationValues() {
		this.dateCreated = LocalDateTime.now(ZoneOffset.UTC);
		this.deleted = false;
	}

	/**
	 * Sets dateModified before update
	 */
	@PreUpdate
	public void setModificationDate() {
		this.dateModified = LocalDateTime.now(ZoneOffset.UTC);
	}

	public LocalDateTime getDateModified() {
		return dateModified;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}