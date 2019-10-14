package com.construction.demo.services;

import java.util.List;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.services.dto.IssuerDto;

/**
 * Business logic for dealing with tender's issuers related stuff.
 * 
 * @author Milivoje Nesic
 *
 */
public interface IssuerService {

	IssuerDto getIssuerByName(final String name) throws ApiException;

	IssuerDto getIssuerById(final Long id) throws ApiException;

	/**
	 * Retrieves all active issuers.
	 * 
	 * @return {@link List<IssuerDto>} or empty list if no Issuers.
	 */
	List<IssuerDto> getAllIssuers() throws ApiException;

	/**
	 * Retrieves all deleted issuers.
	 * 
	 * @return {@link List<IssuerDto>} or empty list if no Issuers.
	 */
	List<IssuerDto> getAllIssuersDeleted() throws ApiException;

}
