package com.construction.demo.services;

import java.util.List;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;
import com.construction.demo.services.dto.BidderDto;
import com.construction.demo.services.dto.OfferDto;
import com.construction.demo.services.dto.TenderDto;

/**
 * Business logic for dealing with tender related stuff.
 * 
 * @author Milivoje Nesic
 *
 */
public interface OfferService {

	/**
	 * Creates a new offer.
	 * 
	 * @param offerDto  {@link OfferDto}
	 * @param tenderId  {@link Long}
	 * @param tbidderId {@link Long}
	 * @return object of {@link OfferDto}
	 * @throws UnprocesableEntityException if no object created
	 */
	OfferDto createOffer(OfferDto offerDto, final Long tenderId, final Long bidder)
			throws UnprocesableEntityException, ApiException;

	/**
	 * Retrieves an offer with specified name.
	 * 
	 * @param name {@link String}
	 * @return object of {@link OfferDto}
	 * @throws ApiException if no object found
	 */
	OfferDto getOfferByName(final String name) throws ApiException;

	/**
	 * Retrieves an offer with specified ID.
	 * 
	 * @param id {@link Long}
	 * @return object of {@link OfferDto}
	 * @throws ApiException if no object found
	 */
	OfferDto getOfferById(final Long id) throws ApiException;

	/**
	 * Retrieves all offers for specified bidder.
	 * 
	 * @param bidderDto {@link BidderDto}
	 * @return {@link List<OfferDto>}
	 * @throws ApiException if no object found
	 */
	List<OfferDto> getAllOffersByBidder(final BidderDto bidderDto) throws ApiException;

	/**
	 * Retrieves all offers for specified bidder and tender.
	 * 
	 * @param bidderDto {@link BidderDto}
	 * @param tenderDto {@link TenderDto}
	 * @return {@link List<OfferDto>}
	 * @throws ApiException if no object found
	 */
	List<OfferDto> getAllOffersByBidderAndTender(final BidderDto bidderDto, final TenderDto tenderDto)
			throws ApiException;

	/**
	 * Retrieves all offers for specified tender.
	 * 
	 * @param TenderDto {@link tenderDto}
	 * @return {@link List<OfferDto>}
	 * @throws ApiException if no object found
	 */
	List<OfferDto> getAllOffersByTender(final TenderDto tenderDto) throws ApiException;

	/**
	 * Retrieves the accepted offer for specified tender.
	 * 
	 * @param TenderDto {@link tenderDto}
	 * @return {@link OfferDto}
	 * @throws ApiException if no object found
	 */
	OfferDto getOfferAcceptedByTender(final TenderDto tenderDto) throws ApiException;

	/**
	 * Retrieves all rejected offers for specified tender.
	 * 
	 * @param TenderDto {@link tenderDto}
	 * @return {@link List<OfferDto>}
	 * @throws ApiException if no object found
	 */
	List<OfferDto> getAllOffersRejectedByTender(final TenderDto tenderDto) throws ApiException;

	/**
	 * Accepted the specified offer, and reject the others belonging to the same
	 * tender.
	 * 
	 * @param offer {@OfferDto} to be accepted
	 * @throws ApiException
	 * @throws ApiException
	 */
	void acceptOfferAndRejectOthersFromTender(OfferDto offer) throws ApiException;

	/**
	 * Accepted the specified offer, and reject the others belonging to the same
	 * tender.
	 * 
	 * @param offer {@OfferDto} to be accepted
	 * @return {@link List<OfferDto>} of rejected offers
	 * @throws ApiException
	 */
	List<OfferDto> rejectOffers(final List<OfferDto> offerList) throws ApiException;

}
