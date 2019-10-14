package com.construction.demo.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.construction.demo.api.model.OfferModel;
import com.construction.demo.api.model.OfferModel.OfferConditionModel;
import com.construction.demo.api.model.TenderModel;
import com.construction.demo.api.model.TenderModel.TenderConditionModel;
import com.construction.demo.services.dto.OfferDto;

@Component
public class OfferDtoToOfferModel extends AbstractConverter<OfferDto, OfferModel> {

	@Override
	protected OfferModel convert(OfferDto source) {
		ModelMapper mapper = new ModelMapper();

		OfferModel offerModel = new OfferModel();
		offerModel.setName(source.getName());
		offerModel.setDescription(source.getDescription());
		offerModel.setId(source.getId());
		offerModel.setTender(mapper.map(source.getTender(), TenderModel.class));
		offerModel.setStatus(source.getStatus());

		List<OfferConditionModel> offerConditionDtoList = source.getOfferConditionList().stream().map(s -> {
			OfferConditionModel conditionModel = new OfferConditionModel();
			conditionModel.setId(s.getId());
			conditionModel.setResponse(s.getResponse());
			conditionModel.setResponseDescription(s.getResponseDescription());
			conditionModel.setTenderCondition((mapper.map(s.getTenderCondition(), TenderConditionModel.class)));

			return conditionModel;
		}).collect(Collectors.toList());
		offerModel.setOfferConditionList(offerConditionDtoList);

		return offerModel;
	}

}
