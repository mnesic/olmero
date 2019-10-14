package com.construction.demo.domain.h2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.demo.common.utils.enums.OfferStatus;
import com.construction.demo.domain.h2.entity.Bidder;
import com.construction.demo.domain.h2.entity.Offer;
import com.construction.demo.domain.h2.entity.Tender;

/**
 * @author Milivoje Nesic
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	Optional<Offer> findByName(final String name);

	Optional<List<Offer>> findByBidder(final Bidder bidder);

	Optional<List<Offer>> findByBidderAndTender(final Bidder bidder, final Tender tender);

	Optional<List<Offer>> findByTender(final Tender tender);

	Optional<List<Offer>> findByTenderAndStatus(final Tender tender, final OfferStatus status);
}