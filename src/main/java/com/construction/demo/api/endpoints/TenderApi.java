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

import com.construction.demo.api.model.TenderRequest;
import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "tender", description = "Tender service API")
@RequestMapping("/tender")
public interface TenderApi {

	@PostMapping(value = "/create", consumes = { "application/json" }, produces = { "application/json" })
	@ApiOperation(value = "Creating a new tender")
	default ResponseEntity<?> createTender(
			@ApiParam(value = "Tender input parameters.", required = true) @RequestBody(required = true) TenderRequest tenderRequest,
			Authorization auth) throws UnprocesableEntityException, ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/list/issuer/{id}", produces = { "application/json" })
	@ApiOperation(value = "Listing all tenders for a specified issuer")
	default ResponseEntity<?> getAllTendersForIssuer(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "id", required = true) Long issuerId,
			Authorization auth) throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { "application/json" })
	@ApiOperation(value = "Listing a tender's details")
	default ResponseEntity<?> getAllTenders(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "id", required = true) Long tenderId)
			throws ApiException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/start/{id}", produces = { "application/json" })
	@ApiOperation(value = "Starting  a tender")
	default ResponseEntity<?> startTender(
			@ApiParam(value = "ID of a tender.", required = true) @PathVariable(name = "id", required = true) Long tenderId,
			Authorization auth) throws ApiException, UnprocesableEntityException {

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
