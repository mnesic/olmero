package com.construction.demo.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.domain.h2.entity.Issuer;
import com.construction.demo.domain.h2.repo.IssuerRepository;
import com.construction.demo.services.IssuerService;
import com.construction.demo.services.dto.IssuerDto;

@Service
public class IssuerServiceImpl implements IssuerService {

	@Autowired
	private IssuerRepository issuerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public IssuerDto getIssuerByName(final String name) throws ApiException {
		Issuer issuerEntity = issuerRepository.findByName(name).orElse(null);
		if (issuerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No issuer objects with NAME: " + name);
		}

		return modelMapper.map(issuerEntity, IssuerDto.class);
	}

	@Override
	public IssuerDto getIssuerById(final Long id) throws ApiException {
		Issuer issuerEntity = issuerRepository.findById(id).orElse(null);
		if (issuerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No issuer objects with ID: " + id);
		}

		return modelMapper.map(issuerEntity, IssuerDto.class);
	}

	@Override
	public List<IssuerDto> getAllIssuers() throws ApiException {
		return getAllIssuersByActivity(true);
	}

	@Override
	public List<IssuerDto> getAllIssuersDeleted() throws ApiException {
		return getAllIssuersByActivity(false);
	}

	/**
	 * Helper class for retrieving issuers by its activity in the system.
	 * 
	 * @param deleted {@link Boolean} determining if the issuer is active
	 * @return {@link List<IssuerDto>}
	 * @throws ApiException
	 */
	private List<IssuerDto> getAllIssuersByActivity(final Boolean deleted) throws ApiException {
		List<Issuer> issuerEntityList = issuerRepository.findByDeleted(deleted).orElse(Collections.emptyList());
		if (issuerEntityList == null || issuerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No issuer object exist with deleted status: " + deleted);
		}

		List<IssuerDto> result = issuerEntityList.stream().map(te -> modelMapper.map(te, IssuerDto.class))
				.collect(Collectors.toList());
		return result;
	}
}
