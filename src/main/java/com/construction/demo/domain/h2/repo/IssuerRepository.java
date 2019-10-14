package com.construction.demo.domain.h2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.demo.domain.h2.entity.Issuer;

/**
 * @author Milivoje Nesic
 */
@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {

	Optional<Issuer> findByName(final String name);

	Optional<List<Issuer>> findByDeleted(final Boolean deleted);

}