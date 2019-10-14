package com.construction.demo.api.endpoints;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.parser.Authorization;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.construction.demo.api.converter.OfferDtoToOfferModel;
import com.construction.demo.api.converter.OfferModelToOfferDto;
import com.construction.demo.api.model.OfferModel;
import com.construction.demo.api.model.OfferRequest;
import com.construction.demo.api.model.OfferResponse;
import com.construction.demo.api.model.SuccessfulResponse;
import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;
import com.construction.demo.common.utils.enums.OfferStatus;
import com.construction.demo.services.BidderService;
import com.construction.demo.services.OfferService;
import com.construction.demo.services.TenderService;
import com.construction.demo.services.dto.BidderDto;
import com.construction.demo.services.dto.OfferDto;
import com.construction.demo.services.dto.TenderDto;

@RestController
public class OfferController implements OfferApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OfferService offerService;

	@Autowired
	private BidderService bidderService;

	@Autowired
	private TenderService tenderService;

	@Autowired
	private OfferModelToOfferDto offerModelToDtoConverter;

	@Autowired
	private OfferDtoToOfferModel offerDtoToModelConverter;

	@Override
	public ResponseEntity<?> createOffer(@RequestBody(required = true) OfferRequest offerRequest, Authorization auth)
			throws UnprocesableEntityException, ApiException {

		LOGGER.info("Started process of creating an Offer");

		modelMapper.addConverter(offerModelToDtoConverter);
		OfferDto offerDto = modelMapper.map(offerRequest.getOffer(), OfferDto.class);
		offerDto.setStatus(OfferStatus.CREATED);
		offerDto = offerService.createOffer(offerDto, offerRequest.getTenderId(), offerRequest.getBidderId());

		return new ResponseEntity<>(new SuccessfulResponse(SuccessfulResponse.CRETAED_OK), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllOffersForSpecifiedBidder(
			@PathVariable(name = "bidderId", required = true) Long bidderId, Authorization auth) throws ApiException {

		BidderDto bidderDto = bidderService.getBidderById(bidderId);
		List<OfferDto> offerDtoList = offerService.getAllOffersByBidder(bidderDto);

		modelMapper.addConverter(offerDtoToModelConverter);
		List<OfferModel> offerList = offerDtoList.stream().map(o -> modelMapper.map(o, OfferModel.class))
				.collect(Collectors.toList());
		OfferResponse response = new OfferResponse(offerList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllOffersForSpecifiedBidderAndSpecifiedTender(
			@PathVariable(name = "tenderId", required = true) Long tenderId,
			@PathVariable(name = "bidderId", required = true) Long bidderId, Authorization auth) throws ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		BidderDto bidderDto = bidderService.getBidderById(bidderId);
		List<OfferDto> offerDtoList = offerService.getAllOffersByBidderAndTender(bidderDto, tenderDto);

		modelMapper.addConverter(offerDtoToModelConverter);
		List<OfferModel> offerList = offerDtoList.stream().map(o -> modelMapper.map(o, OfferModel.class))
				.collect(Collectors.toList());
		OfferResponse response = new OfferResponse(offerList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllOffersForSpecifiedTender(
			@PathVariable(name = "tenderId", required = true) Long tenderId, Authorization auth) throws ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		List<OfferDto> offerDtoList = offerService.getAllOffersByTender(tenderDto);

		modelMapper.addConverter(offerDtoToModelConverter);
		List<OfferModel> offerList = offerDtoList.stream().map(o -> modelMapper.map(o, OfferModel.class))
				.collect(Collectors.toList());
		OfferResponse response = new OfferResponse(offerList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllRejectedOffersForSpecifiedTender(
			@PathVariable(name = "tenderId", required = true) Long tenderId, Authorization auth) throws ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		List<OfferDto> offerDtoList = offerService.getAllOffersRejectedByTender(tenderDto);

		modelMapper.addConverter(offerDtoToModelConverter);
		List<OfferModel> offerList = offerDtoList.stream().map(o -> modelMapper.map(o, OfferModel.class))
				.collect(Collectors.toList());
		OfferResponse response = new OfferResponse(offerList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllAcceptedOffersForSpecifiedTender(
			@PathVariable(name = "tenderId", required = true) Long tenderId, Authorization auth) throws ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		OfferDto offerDto = offerService.getOfferAcceptedByTender(tenderDto);

		modelMapper.addConverter(offerDtoToModelConverter);
		List<OfferModel> offerList = Arrays.asList(new OfferModel[] { modelMapper.map(offerDto, OfferModel.class) });
		OfferResponse response = new OfferResponse(offerList);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> acceptOffer(@PathVariable(name = "offerId", required = true) Long offerId,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		OfferDto offerDto = offerService.getOfferById(offerId);
		offerService.acceptOfferAndRejectOthersFromTender(offerDto);

		return new ResponseEntity<>(new SuccessfulResponse(SuccessfulResponse.UPDATED_OK), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> rejectOffer(@PathVariable(name = "offerId", required = true) Long offerId,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		OfferDto offerDto = offerService.getOfferById(offerId);
		offerService.rejectOffers(Arrays.asList(new OfferDto[] { offerDto }));

		return new ResponseEntity<>(new SuccessfulResponse(SuccessfulResponse.UPDATED_OK), HttpStatus.OK);

	}

}
