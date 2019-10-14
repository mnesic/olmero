package com.construction.demo.api.endpoints;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.parser.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.construction.demo.api.model.SuccessfulResponse;
import com.construction.demo.api.model.TenderModel;
import com.construction.demo.api.model.TenderRequest;
import com.construction.demo.api.model.TenderResponse;
import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;
import com.construction.demo.services.IssuerService;
import com.construction.demo.services.TenderService;
import com.construction.demo.services.dto.IssuerDto;
import com.construction.demo.services.dto.TenderDto;

@RestController
public class TenderController implements TenderApi {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TenderService tenderService;

	@Autowired
	private IssuerService issuerService;

	@Override
	public ResponseEntity<?> createTender(@RequestBody(required = true) TenderRequest tenderRequest, Authorization auth)
			throws UnprocesableEntityException, ApiException {

		TenderDto tenderDto = modelMapper.map(tenderRequest.getTender(), TenderDto.class);
		tenderDto.setStarted(false);
		tenderDto = tenderService.createTender(tenderDto, tenderRequest.getIssuerId());

		return new ResponseEntity<>(new SuccessfulResponse(SuccessfulResponse.CRETAED_OK), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllTendersForIssuer(@PathVariable(name = "id", required = true) Long issuerId,
			Authorization auth) throws ApiException {

		IssuerDto issuerDto = issuerService.getIssuerById(issuerId);
		List<TenderDto> tenderDtoList = tenderService.getAllTendersByIssuer(issuerDto);

		List<TenderModel> tenderList = tenderDtoList.stream().map(t -> modelMapper.map(t, TenderModel.class))
				.collect(Collectors.toList());
		TenderResponse response = new TenderResponse(tenderList);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getAllTenders(@PathVariable(name = "id", required = true) Long tenderId)
			throws ApiException {

		TenderDto tenderDto = tenderService.getTenderById(tenderId);
		TenderResponse response = new TenderResponse(
				Arrays.asList(new TenderModel[] { modelMapper.map(tenderDto, TenderModel.class) }));
		return ResponseEntity.ok(response);

	}

	@Override
	public ResponseEntity<?> startTender(@PathVariable(name = "id", required = true) Long tenderId, Authorization auth)
			throws ApiException, UnprocesableEntityException {

		tenderService.startTender(tenderId);
		return new ResponseEntity<>(new SuccessfulResponse(SuccessfulResponse.UPDATED_OK), HttpStatus.OK);

	}

}
