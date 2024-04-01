package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Cinemas;
import com.ebos.tables.Movies;

@Repository
public interface CinemaRepository extends JpaRepository<Cinemas, Long> {

	Optional<Cinemas> findByCinemaName(String cinemaName);

	 
	}
