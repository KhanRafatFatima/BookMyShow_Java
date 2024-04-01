package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long>{

	Optional<Movies> findByMovieName(String movieName);

}
