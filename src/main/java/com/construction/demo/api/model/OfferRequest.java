/**
 * 
 */
package com.construction.demo.api.model;

public class OfferRequest {

	private Long tenderId;

	private Long bidderId;

	private OfferModel offer;

	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}

	public OfferModel getOffer() {
		return offer;
	}

	public void setOffer(OfferModel offer) {
		this.offer = offer;
	}

	public Long getBidderId() {
		return bidderId;
	}

	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}

}
