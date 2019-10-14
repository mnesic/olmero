/**
 * 
 */
package com.construction.demo.api.model;

import java.util.List;

import com.construction.demo.api.model.TenderModel.TenderConditionModel;
import com.construction.demo.common.utils.enums.OfferStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfferModel {

	private Long id;

	private String name;

	private String description;

	private TenderModel tender;

	private BidderModel bidder;

	private List<OfferConditionModel> offerConditionList;

	private OfferStatus status;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TenderModel getTender() {
		return tender;
	}

	public void setTender(TenderModel tender) {
		this.tender = tender;
	}

	public BidderModel getBidder() {
		return bidder;
	}

	public void setBidder(BidderModel bidder) {
		this.bidder = bidder;
	}

	public List<OfferConditionModel> getOfferConditionList() {
		return offerConditionList;
	}

	public void setOfferConditionList(List<OfferConditionModel> offerConditionList) {
		this.offerConditionList = offerConditionList;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public static class OfferConditionModel {

		private Long id;

		private String response;

		private String responseDescription;

		private TenderConditionModel tenderCondition;

		private Long tenderConditionId;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public TenderConditionModel getTenderCondition() {
			return tenderCondition;
		}

		public void setTenderCondition(TenderConditionModel tenderCondition) {
			this.tenderCondition = tenderCondition;
		}

		public Long getTenderConditionId() {
			return tenderConditionId;
		}

		public void setTenderConditionId(Long tenderConditionId) {
			this.tenderConditionId = tenderConditionId;
		}

	}

	public static class BidderModel {

		private Long id;

		private String address;

		private String name;

		private String description;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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

}