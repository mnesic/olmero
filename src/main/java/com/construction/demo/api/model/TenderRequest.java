/**
 * 
 */
package com.construction.demo.api.model;

public class TenderRequest {

	private Long issuerId;

	private TenderModel tender;

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	public TenderModel getTender() {
		return tender;
	}

	public void setTender(TenderModel tender) {
		this.tender = tender;
	}
}
