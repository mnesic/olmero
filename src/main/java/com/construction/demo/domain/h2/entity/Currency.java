/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CURRENCY")
public class Currency extends BaseEntityAudit {

	@NotNull
	@Column(unique = true, length = 32)
	private String name;

	@NotNull
	@Column(unique = true, length = 3)
	private String code;

	@NotNull
	@Column(name = "DEFAULT_")
	private Boolean main;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

}