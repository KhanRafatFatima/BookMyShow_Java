package com.ebos.Service;

import java.util.Map;

import com.ebos.Request.AddCinemaRequest;
import com.ebos.Request.AddMovieRequest;
import com.ebos.Request.AddPriceRequest;
import com.ebos.Request.AddSeatRequest;
import com.ebos.Request.AddShowRequest;
import com.ebos.Response.AddMovieResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;

public interface AdminService {
	
	public AddMovieResponse addMovie(AddMovieRequest addMovieRequest);
	
	public DeleteResponse deleteMovie(Long movieId);
	
	public ApiResponse addCinema(AddCinemaRequest addCinemaRequest);
	
	public DeleteResponse deleteCinema(Long cinemaId);
	
	public Map<String, Object> addShows(AddShowRequest addShowRequest);
	
	public Map<String, Object> deleteShows(Long id);
	
	public Map<String, Object> addPrice(AddPriceRequest addPriceRequest);
	
	public Map<String, Object> addSeat(AddSeatRequest addSeatRequest);	
	
//	public Map<String, V>
	
	
}
