package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{

	Optional<Price> findByPrice(Long price);

}
