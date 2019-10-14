/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OFFER_CONDITION")
public class OfferCondition extends BaseEntityAudit {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "OFFER_ID")
	private Offer offer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TENDER_CONDITION_ID")
	private TenderCondition tenderCondition;

	@Column(length = 256)
	private String response;

	@Column(name = "RESPONSE_DESCRIPTION", length = 256)
	private String responseDescription;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public TenderCondition getTenderCondition() {
		return tenderCondition;
	}

	public void setTenderCondition(TenderCondition tenderCondition) {
		this.tenderCondition = tenderCondition;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

}