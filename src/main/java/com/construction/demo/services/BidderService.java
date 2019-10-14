package com.construction.demo.services;

import java.util.List;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.services.dto.BidderDto;

/**
 * Business logic for dealing with tender's bidders related stuff.
 * 
 * @author Milivoje Nesic
 *
 */
public interface BidderService {

	/**
	 * Retrieves a bidder with specified name.
	 * 
	 * @param name {@link String}
	 * @return object if {@link BidderDto}
	 * @throws ApiException if no object found
	 */
	BidderDto getBidderByName(final String name) throws ApiException;

	/**
	 * Retrieves a bidder with specified ID.
	 * 
	 * @param name {@link Long}
	 * @return object of {@link BidderDto}
	 * @throws ApiException if no object found
	 */
	BidderDto getBidderById(final Long id) throws ApiException;

	/**
	 * Retrieves all active bidders.
	 * 
	 * @return object of {@link List<BidderDto>}
	 * @throws ApiException if no object found
	 */
	List<BidderDto> getAllBidders() throws ApiException;

	/**
	 * Retrieves all deleted Bidders.
	 * 
	 * @return object of {@link List<BidderDto>}
	 * @throws ApiException if no object found
	 */
	List<BidderDto> getAllBiddersDeleted() throws ApiException;

}
