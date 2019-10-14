package com.construction.demo.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.domain.h2.entity.Bidder;
import com.construction.demo.domain.h2.repo.BidderRepository;
import com.construction.demo.services.BidderService;
import com.construction.demo.services.dto.BidderDto;

@Service
public class BidderServiceImpl implements BidderService {

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BidderDto getBidderByName(final String name) throws ApiException {
		Bidder bidderEntity = bidderRepository.findByName(name).orElse(null);
		if (bidderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No bidder objects with NAME: " + name);
		}

		return modelMapper.map(bidderEntity, BidderDto.class);
	}

	@Override
	public BidderDto getBidderById(final Long id) throws ApiException {
		Bidder bidderEntity = bidderRepository.findById(id).orElse(null);
		if (bidderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No bidder objects with NAME: " + id);
		}

		return modelMapper.map(bidderEntity, BidderDto.class);
	}

	@Override
	public List<BidderDto> getAllBidders() throws ApiException {
		return getAllBiddersByActivity(true);
	}

	@Override
	public List<BidderDto> getAllBiddersDeleted() throws ApiException {
		return getAllBiddersByActivity(false);
	}

	/**
	 * Helper class for retrieving bidders by its activity in the system.
	 * 
	 * @param deleted {@link Boolean} determining if the bidder is active
	 * @return {@link List<BidderDto>}
	 * @throws ApiException
	 */
	private List<BidderDto> getAllBiddersByActivity(final Boolean deleted) throws ApiException {
		List<Bidder> bidderEntityList = bidderRepository.findByDeleted(deleted).orElse(Collections.emptyList());
		if (bidderEntityList == null || bidderEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No bidder object exist with deleted status: " + deleted);
		}

		List<BidderDto> result = bidderEntityList.stream().map(te -> modelMapper.map(te, BidderDto.class))
				.collect(Collectors.toList());
		return result;
	}
}
