package com.construction.demo.api.endpoints;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.construction.demo.api.model.OfferRequest;
import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "offer", description = "Offer service API")
@RequestMapping("/offer")
public interface OfferApi {

	@PostMapping(value = "/create", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Creating a new offer")
	default ResponseEntity<?> createOffer(
			@ApiParam(value = "Offer input parameters.", required = true) @RequestBody(required = true) OfferRequest offerRequest,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/list/bid/{bidderId}", produces = { "application/json" })
	@ApiOperation(value = "Listing all offers for a specified bidder")
	default ResponseEntity<?> getAllOffersForSpecifiedBidder(
			@ApiParam(value = "ID of a bidder.", required = true) @PathVariable(name = "bidderId", required = true) Long bidderId,
			Authorization auth) throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/list/bid/{bidderId}/tender/{tenderId}", produces = { "application/json" })
	@ApiOperation(value = "Listing all offers for a specified bidder and a specified tender")
	default ResponseEntity<?> getAllOffersForSpecifiedBidderAndSpecifiedTender(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "tenderId", required = true) Long tenderId,
			@ApiParam(value = "ID of a bidder.", required = true) @PathVariable(name = "bidderId", required = true) Long bidderId,
			Authorization auth) throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/list/tender/{tenderId}", produces = { "application/json" })
	@ApiOperation(value = "Listing all offers for a specified tender")
	default ResponseEntity<?> getAllOffersForSpecifiedTender(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "tenderId", required = true) Long tenderId,
			Authorization auth) throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/rejected/list/tender/{tenderId}", produces = { "application/json" })
	@ApiOperation(value = "Listing all rejected offers for a specified tender")
	default ResponseEntity<?> getAllRejectedOffersForSpecifiedTender(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "tenderId", required = true) Long tenderId,
			Authorization auth) throws ApiException {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/accepted/list/tender/{tenderId}", produces = { "application/json" })
	@ApiOperation(value = "Listing an accepted offer for a specified tender")
	default ResponseEntity<?> getAllAcceptedOffersForSpecifiedTender(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "tenderId", required = true) Long tenderId,
			Authorization auth) throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PutMapping(value = "/accept/{offerId}", produces = { "application/json" })
	@ApiOperation(value = "Accepting an offer, and rejecting the remaining ones")
	default ResponseEntity<?> acceptOffer(
			@ApiParam(value = "ID of a offer.", required = true) @PathVariable(name = "offerId", required = true) Long offerId,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PutMapping(value = "/reject/{offerId}", produces = { "application/json" })
	@ApiOperation(value = "Rejecting an offer")
	default ResponseEntity<?> rejectOffer(
			@ApiParam(value = "ID of a offer.", required = true) @PathVariable(name = "offerId", required = true) Long offerId,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
