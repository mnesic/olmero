package com.construction.demo.domain.h2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.demo.domain.h2.entity.Bidder;

/**
 * @author Milivoje Nesic
 */
@Repository
public interface BidderRepository extends JpaRepository<Bidder, Long> {

	Optional<Bidder> findByName(String name);

	Optional<List<Bidder>> findByDeleted(final Boolean deleted);
}