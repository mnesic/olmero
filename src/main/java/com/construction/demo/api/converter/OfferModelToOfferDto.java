package com.construction.demo.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.construction.demo.api.model.OfferModel;
import com.construction.demo.services.dto.OfferConditionDto;
import com.construction.demo.services.dto.OfferDto;

@Component
public class OfferModelToOfferDto extends AbstractConverter<OfferModel, OfferDto> {

	@Override
	protected OfferDto convert(OfferModel source) {
		OfferDto offerDto = new OfferDto();
		offerDto.setName(source.getName());
		offerDto.setDescription(source.getDescription());
		
		List<OfferConditionDto> offerConditionDtoList = source.getOfferConditionList().stream().map(s -> {
			OfferConditionDto conditionDto = new OfferConditionDto();
			conditionDto.setResponse(s.getResponse());
			conditionDto.setResponseDescription(s.getResponseDescription());
			conditionDto.setTenderConditionId(s.getTenderConditionId());

			return conditionDto;
		}).collect(Collectors.toList());
		offerDto.setOfferConditionList(offerConditionDtoList);

		return offerDto;
	}

}
