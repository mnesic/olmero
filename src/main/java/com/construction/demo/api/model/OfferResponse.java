/**
 * 
 */
package com.construction.demo.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfferResponse {

	private List<OfferModel> offerList;

	public OfferResponse(List<OfferModel> offerList) {
		this.offerList = offerList;
	}

	public List<OfferModel> getOfferList() {
		return offerList;
	}

}