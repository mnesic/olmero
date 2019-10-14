package com.construction.demo.services.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.construction.demo.domain.h2.entity.Bidder;
import com.construction.demo.domain.h2.entity.Offer;
import com.construction.demo.domain.h2.entity.OfferCondition;
import com.construction.demo.domain.h2.entity.Tender;
import com.construction.demo.domain.h2.entity.TenderCondition;
import com.construction.demo.services.dto.OfferDto;

@Component
public class OfferDtoToOffer extends AbstractConverter<OfferDto, Offer> {

	@Override
	protected Offer convert(OfferDto source) {
		ModelMapper mapper = new ModelMapper();

		Offer offer = new Offer();
		offer.setName(source.getName());
		offer.setDescription(source.getDescription());
		offer.setStatus(source.getStatus());
		offer.setTender(mapper.map(source.getTender(), Tender.class));
		offer.setBidder(mapper.map(source.getBidder(), Bidder.class));

		List<OfferCondition> offerConditionList = source.getOfferConditionList().stream().map(s -> {
			OfferCondition condition = new OfferCondition();
			condition.setOffer(offer);
			condition.setResponse(s.getResponse());
			condition.setResponseDescription(s.getResponseDescription());
			condition.setTenderCondition(mapper.map(s.getTenderCondition(), TenderCondition.class));

			return condition;
		}).collect(Collectors.toList());

		offer.setOfferConditionList(offerConditionList);

		return offer;
	}

}
