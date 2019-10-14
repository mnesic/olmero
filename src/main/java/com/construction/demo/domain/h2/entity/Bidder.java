/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BIDDER")
public class Bidder extends BaseEntityAudit {

	@Column(length = 256)
	private String address;

	@NotNull
	@Column(length = 64)
	private String name;

	@Column(length = 256)
	private String description;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}