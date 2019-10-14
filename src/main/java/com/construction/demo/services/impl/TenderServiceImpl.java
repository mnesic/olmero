package com.construction.demo.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;
import com.construction.demo.domain.h2.entity.Issuer;
import com.construction.demo.domain.h2.entity.Tender;
import com.construction.demo.domain.h2.repo.TenderRepository;
import com.construction.demo.services.IssuerService;
import com.construction.demo.services.TenderService;
import com.construction.demo.services.dto.IssuerDto;
import com.construction.demo.services.dto.TenderDto;

@Service
public class TenderServiceImpl implements TenderService {

	@Autowired
	private TenderRepository tenderRepository;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private ModelMapper modelMapper;

	public TenderDto createTender(TenderDto tenderDto, Long issuerId) throws UnprocesableEntityException, ApiException {

		IssuerDto issuerDto = issuerService.getIssuerById(issuerId);
		tenderDto.setIssuer(issuerDto);

		Tender tenderEntity = modelMapper.map(tenderDto, Tender.class);
		tenderEntity = tenderRepository.save(tenderEntity);
		if (tenderEntity == null) {
			throw new UnsupportedOperationException("Data for Tenderobject could not be processed and saved.");
		}

		return modelMapper.map(tenderEntity, TenderDto.class);
	}

	@Override
	public TenderDto startTender(Long tenderId) throws UnprocesableEntityException {
		Tender tenderEntity = tenderRepository.findById(tenderId).orElse(null);

		if (tenderEntity != null && !tenderEntity.getStarted()) {
			tenderEntity.setStarted(true);
			tenderEntity = tenderRepository.save(tenderEntity);

			return modelMapper.map(tenderEntity, TenderDto.class);
		} else {
			throw new UnsupportedOperationException("Tender object could not be processed and started.");
		}
	}

	@Override
	public TenderDto getTenderByName(final String name) throws ApiException {
		Tender tenderEntity = tenderRepository.findByName(name).orElse(null);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No tender objects with NAME: " + name);
		}

		return modelMapper.map(tenderEntity, TenderDto.class);
	}

	@Override
	public TenderDto getTenderById(final Long id) throws ApiException {
		Tender tenderEntity = tenderRepository.findById(id).orElse(null);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No tender objects with ID: " + id);
		}

		return modelMapper.map(tenderEntity, TenderDto.class);
	}

	@Override
	public List<TenderDto> getAllTendersByIssuer(final IssuerDto issuerDto) throws ApiException {
		Issuer issuerEntity = modelMapper.map(issuerDto, Issuer.class);
		if (issuerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No issuer object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<Tender> tenderEntityList = tenderRepository.findByIssuer(issuerEntity).orElse(Collections.emptyList());
		if (tenderEntityList == null || tenderEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No tender object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<TenderDto> result = tenderEntityList.stream().map(te -> modelMapper.map(te, TenderDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<TenderDto> getAllTendersOpenedByIssuer(IssuerDto issuerDto) throws ApiException {
		Issuer issuerEntity = modelMapper.map(issuerDto, Issuer.class);
		if (issuerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No issuer object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<Tender> tenderEntityList = tenderRepository.findByIssuerAndStarted(issuerEntity, true)
				.orElse(Collections.emptyList());
		if (tenderEntityList == null || tenderEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No opened tender object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<TenderDto> result = tenderEntityList.stream().map(te -> modelMapper.map(te, TenderDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<TenderDto> getAllTendersClosedByIssuer(IssuerDto issuerDto) throws ApiException {
		Issuer issuerEntity = modelMapper.map(issuerDto, Issuer.class);
		if (issuerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No issuer object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<Tender> tenderEntityList = tenderRepository.findByIssuerAndStarted(issuerEntity, false)
				.orElse(Collections.emptyList());
		if (tenderEntityList == null || tenderEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No closed tender object exist for specified issuer ID: " + issuerDto.getId());
		}

		List<TenderDto> result = tenderEntityList.stream().map(te -> modelMapper.map(te, TenderDto.class))
				.collect(Collectors.toList());
		return result;
	}

}
