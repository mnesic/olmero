package com.construction.demo.services;

import java.util.List;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;
import com.construction.demo.services.dto.IssuerDto;
import com.construction.demo.services.dto.TenderDto;

/**
 * Business logic for dealing with tender related stuff.
 * 
 * @author Milivoje Nesic
 *
 */
public interface TenderService {

	/**
	 * Creates new tender.
	 * 
	 * @param tenderDto {@link TenderDto}
	 * @param issuerId  {@link Long}
	 * @return object of {@link TenderDto}
	 * @throws UnprocesableEntityException if no object created
	 */
	TenderDto createTender(TenderDto tenderDto, Long issuerId) throws UnprocesableEntityException, ApiException;

	/**
	 * Updates specified tender.
	 * 
	 * @param tenderId {@link Long}
	 * @return object of {@link TenderDto}
	 * @throws UnprocesableEntityException if no object created
	 */
	TenderDto startTender(Long tenderId) throws UnprocesableEntityException;

	/**
	 * Retrieves an offer with specified name.
	 * 
	 * @param name {@link String}
	 * @return object of {@link TenderDto}
	 * @throws ApiException if no object found
	 * 
	 */
	TenderDto getTenderByName(final String name) throws ApiException;

	/**
	 * Retrieves an offer with specified ID.
	 * 
	 * @param name {@link Long}
	 * @return object of {@link TenderDto}
	 * @throws ApiException if no object found
	 * 
	 */
	TenderDto getTenderById(final Long id) throws ApiException;

	/**
	 * Retrieves all {@link TenderDto} either opened or closed.
	 * 
	 * @param issuerDto {@link IssuerDto}
	 * @return {@link List<TenderDto>}
	 * @throws ApiException
	 */
	List<TenderDto> getAllTendersByIssuer(final IssuerDto issuerDto) throws ApiException;

	/**
	 * Retrieves just opened {@link TenderDto}.
	 * 
	 * @param issuerDto {@link IssuerDto}
	 * @return {@link List<TenderDto>}
	 * @throws ApiException
	 */
	List<TenderDto> getAllTendersOpenedByIssuer(final IssuerDto issuerDto) throws ApiException;

	/**
	 * Retrieves just closed {@link TenderDto}.
	 * 
	 * @param issuerDto {@link IssuerDto}
	 * @return {@link List<TenderDto>}
	 * @throws ApiException
	 */
	List<TenderDto> getAllTendersClosedByIssuer(final IssuerDto issuerDto) throws ApiException;

}
