package com.construction.demo.domain.h2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.demo.domain.h2.entity.Issuer;
import com.construction.demo.domain.h2.entity.Tender;

/**
 * @author Milivoje Nesic
 */
@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

	Optional<Tender> findByName(final String name);

	Optional<List<Tender>> findByIssuer(final Issuer issuer);

	Optional<List<Tender>> findByIssuerAndStarted(final Issuer issuer, final Boolean started);

}