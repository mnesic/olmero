package com.construction.demo.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.utils.enums.OfferStatus;
import com.construction.demo.domain.h2.entity.Bidder;
import com.construction.demo.domain.h2.entity.Offer;
import com.construction.demo.domain.h2.entity.Tender;
import com.construction.demo.domain.h2.repo.OfferRepository;
import com.construction.demo.services.BidderService;
import com.construction.demo.services.OfferService;
import com.construction.demo.services.TenderService;
import com.construction.demo.services.converter.OfferDtoToOffer;
import com.construction.demo.services.dto.BidderDto;
import com.construction.demo.services.dto.OfferDto;
import com.construction.demo.services.dto.TenderConditionDto;
import com.construction.demo.services.dto.TenderDto;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private TenderService tenderService;

	@Autowired
	private BidderService bidderService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OfferDtoToOffer offerConverter;

	@Override
	public OfferDto createOffer(OfferDto offerDto, final Long tenderId, final Long bidderId)
			throws UnsupportedOperationException, ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		BidderDto bidderDto = bidderService.getBidderById(bidderId);

		offerDto.setTender(tenderDto);
		offerDto.setBidder(bidderDto);
		offerDto.getOfferConditionList().stream().forEach(oc -> {
			Long tenderConditionId = oc.getTenderConditionId();
			TenderConditionDto tenderConditionDto = tenderDto.getTenderConditionList().stream()
					.filter(tc -> tc.getId().equals(tenderConditionId)).findAny().orElse(null);

			if (tenderConditionDto != null) {
				oc.setTenderCondition(tenderConditionDto);
			}
		});
		modelMapper.addConverter(offerConverter);
		Offer offerEntity = modelMapper.map(offerDto, Offer.class);
		offerEntity = offerRepository.save(offerEntity);

		if (offerEntity == null) {
			throw new UnsupportedOperationException("Data for Offer object could not be processed and saved.");
		}

		return modelMapper.map(offerEntity, OfferDto.class);
	}

	@Override
	public OfferDto getOfferByName(final String name) throws ApiException {
		Offer offerEntity = offerRepository.findByName(name).orElse(null);

		if (offerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No offer objects with NAME: " + name);
		}

		return modelMapper.map(offerEntity, OfferDto.class);
	}

	@Override
	public OfferDto getOfferById(final Long id) throws ApiException {
		Offer offerEntity = offerRepository.findById(id).orElse(null);

		if (offerEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No offer objects with ID: " + id);
		}

		return modelMapper.map(offerEntity, OfferDto.class);
	}

	@Override
	public List<OfferDto> getAllOffersByBidder(final BidderDto bidderDto) throws ApiException {
		Bidder bidderEntity = modelMapper.map(bidderDto, Bidder.class);
		if (bidderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No bidder object exist for specified bidder ID: " + bidderDto.getId());
		}

		List<Offer> offerEntityList = offerRepository.findByBidder(bidderEntity).orElse(Collections.emptyList());
		if (offerEntityList == null || offerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No offer object exist for specified bidder ID: " + bidderDto.getId());
		}

		List<OfferDto> result = offerEntityList.stream().map(oe -> modelMapper.map(oe, OfferDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<OfferDto> getAllOffersByBidderAndTender(final BidderDto bidderDto, final TenderDto tenderDto)
			throws ApiException {
		Bidder bidderEntity = modelMapper.map(bidderDto, Bidder.class);
		if (bidderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No bidder object exist for specified bidder ID: " + bidderDto.getId());
		}

		Tender tenderEntity = modelMapper.map(tenderDto, Tender.class);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No tender object exist for specified bidder ID: " + tenderDto.getId());
		}

		List<Offer> offerEntityList = offerRepository.findByBidderAndTender(bidderEntity, tenderEntity)
				.orElse(Collections.emptyList());
		if (offerEntityList == null || offerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("No offer object exist for specified bidder ID: {} and tender ID: {}",
							bidderDto.getId(), tenderDto.getId()));
		}

		List<OfferDto> result = offerEntityList.stream().map(oe -> modelMapper.map(oe, OfferDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<OfferDto> getAllOffersByTender(final TenderDto tenderDto) throws ApiException {
		Tender tenderEntity = modelMapper.map(tenderDto, Tender.class);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No tender object exist for specified bidder ID: " + tenderDto.getId());
		}

		List<Offer> offerEntityList = offerRepository.findByTender(tenderEntity).orElse(null);
		if (offerEntityList == null || offerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No offer object exist for specified tender ID: " + tenderDto.getId());
		}

		List<OfferDto> result = offerEntityList.stream().map(oe -> modelMapper.map(oe, OfferDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public OfferDto getOfferAcceptedByTender(final TenderDto tenderDto) throws ApiException {
		Tender tenderEntity = modelMapper.map(tenderDto, Tender.class);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No tender object exist for specified bidder ID: " + tenderDto.getId());
		}

		List<Offer> offerEntityList = offerRepository.findByTenderAndStatus(tenderEntity, OfferStatus.ACCEPTED)
				.orElse(Collections.emptyList());
		if (offerEntityList == null || offerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No accepted offer object exist for specified tender ID: " + tenderDto.getId());
		}

		return modelMapper.map(offerEntityList.get(0), OfferDto.class);
	}

	@Override
	public List<OfferDto> getAllOffersRejectedByTender(final TenderDto tenderDto) throws ApiException {
		Tender tenderEntity = modelMapper.map(tenderDto, Tender.class);
		if (tenderEntity == null) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No tender object exist for specified bidder ID: " + tenderDto.getId());
		}

		List<Offer> offerEntityList = offerRepository.findByTenderAndStatus(tenderEntity, OfferStatus.REJECTED)
				.orElse(Collections.emptyList());
		if (offerEntityList == null || offerEntityList.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND,
					"No rejected offer object exist for specified tender ID: " + tenderDto.getId());
		}

		List<OfferDto> result = offerEntityList.stream().map(oe -> modelMapper.map(oe, OfferDto.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public void acceptOfferAndRejectOthersFromTender(OfferDto offer) throws ApiException {
		if (offer != null) {
			List<OfferDto> offerDtoList = this.getAllOffersByTender(offer.getTender());
			if (offerDtoList == null || offerDtoList.isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND,
						String.format("Invalid offer ID: {}, no tender reletad data exist.: ", offer.getId()));
			}

			if (offerDtoList.stream().anyMatch(o -> o.getStatus().equals(OfferStatus.ACCEPTED))) {
				throw new ApiException(HttpStatus.NOT_ACCEPTABLE,
						"Offer could not be accepted, already exists an accepted offer. Tender ID: " + offer.getId());
			} else {
				offerDtoList.stream().forEach(o -> {
					if (o.getId().equals(offer.getId())) {
						o.setStatus(OfferStatus.ACCEPTED);
					} else {
						o.setStatus(OfferStatus.REJECTED);
					}
				});

				List<Offer> offerEntityList = offerDtoList.stream().map(od -> modelMapper.map(od, Offer.class))
						.collect(Collectors.toList());
				offerRepository.saveAll(offerEntityList);
			}
		}
	}

	@Override
	public List<OfferDto> rejectOffers(final List<OfferDto> offerDtoList) throws ApiException {

		List<OfferDto> offerRejectList = offerDtoList.stream().map(od -> {

			if (od.getStatus().equals(OfferStatus.SUBMITTED)) {
				od.setStatus(OfferStatus.REJECTED);
			}
			return od;
		}).collect(Collectors.toList());

		if (offerRejectList == null || offerRejectList.isEmpty()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "No offers to be rejected");
		}
		List<Offer> offerEntityList = offerRejectList.stream().map(od -> modelMapper.map(od, Offer.class))
				.collect(Collectors.toList());
		offerRepository.saveAll(offerEntityList);

		return offerRejectList;

	}

}
