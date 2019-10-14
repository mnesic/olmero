package com.construction.demo.domain.h2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.construction.demo.domain.h2.entity.Currency;

/**
 * @author Milivoje Nesic
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	Optional<Currency> findByCode(final String code);

	@Query(value = "SELECT * FROM CURRENCY c WHERE c.DEFAULT_ = 1", nativeQuery = true)
	Optional<Currency> findDefault();

}