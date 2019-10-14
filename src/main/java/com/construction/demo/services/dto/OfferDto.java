/**
 * 
 */
package com.construction.demo.services.dto;

import java.util.List;

import com.construction.demo.common.utils.enums.OfferStatus;

public class OfferDto {

	private Long id;

	private BidderDto bidder;

	private TenderDto tender;

	private String name;

	private String description;

	private OfferStatus status;

	private List<OfferConditionDto> offerConditionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BidderDto getBidder() {
		return bidder;
	}

	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}

	public TenderDto getTender() {
		return tender;
	}

	public void setTender(TenderDto tender) {
		this.tender = tender;
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

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public List<OfferConditionDto> getOfferConditionList() {
		return offerConditionList;
	}

	public void setOfferConditionList(List<OfferConditionDto> offerConditionList) {
		this.offerConditionList = offerConditionList;
	}

}