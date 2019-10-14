/**
 * 
 */
package com.construction.demo.services.dto;

public class OfferConditionDto {

	private Long id;

	private OfferDto offer;

	private TenderConditionDto tenderCondition;

	private Long tenderConditionId;

	private String response;

	private String responseDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfferDto getOffer() {
		return offer;
	}

	public void setOffer(OfferDto offer) {
		this.offer = offer;
	}

	public TenderConditionDto getTenderCondition() {
		return tenderCondition;
	}

	public void setTenderCondition(TenderConditionDto tenderCondition) {
		this.tenderCondition = tenderCondition;
	}

	public Long getTenderConditionId() {
		return tenderConditionId;
	}

	public void setTenderConditionId(Long tenderConditionId) {
		this.tenderConditionId = tenderConditionId;
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