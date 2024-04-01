package com.ebos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Movie_Reviews;
import com.ebos.tables.Movies;

@Repository
public interface MovieReviewRepository extends JpaRepository<Movie_Reviews, Long>{

	List<Movie_Reviews> findByMovie(Movies movie);

	Optional<Movie_Reviews> findByMovie_MovieNameAndUser_UserId(String movieName, Long userId);


//	List<Movie_Reviews> findByMovieName(String movieName);
//
//	List<Movie_Reviews> findByMovie(Movies movie);

}
