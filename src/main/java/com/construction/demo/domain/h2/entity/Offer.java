/**
 * 
 */
package com.construction.demo.domain.h2.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.construction.demo.common.utils.enums.OfferStatus;

@Entity
@Table(name = "OFFER")
public class Offer extends BaseEntityAudit {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "BIDDER_ID")
	private Bidder bidder;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TENDER_ID")
	private Tender tender;

	@NotNull
	@Column(length = 64)
	private String name;

	@Column(length = 256)
	private String description;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 256)
	private OfferStatus status;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "offer")
	private List<OfferCondition> offerConditionList;

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
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

	public List<OfferCondition> getOfferConditionList() {
		return offerConditionList;
	}

	public void setOfferConditionList(List<OfferCondition> offerConditionList) {
		this.offerConditionList = offerConditionList;
	}

}