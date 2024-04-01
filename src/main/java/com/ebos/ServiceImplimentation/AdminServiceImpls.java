package com.ebos.ServiceImplimentation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.startup.Catalina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddCinemaRequest;
import com.ebos.Request.AddMovieRequest;
import com.ebos.Request.AddPriceRequest;
import com.ebos.Request.AddSeatRequest;
import com.ebos.Request.AddShowRequest;
import com.ebos.Response.AddMovieResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;
import com.ebos.Service.AdminService;
import com.ebos.repository.CinemaRepository;
import com.ebos.repository.MovieRepository;
import com.ebos.repository.PriceRepository;
import com.ebos.repository.SeatRepository;
import com.ebos.repository.ShowRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Cinemas;
import com.ebos.tables.Movies;
import com.ebos.tables.Price;
import com.ebos.tables.Seat;
import com.ebos.tables.Shows;
import com.ebos.tables.User;

@Service
public class AdminServiceImpls implements AdminService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	CinemaRepository cinemaRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	SeatRepository seatRepository;

	@Override
	public AddMovieResponse addMovie(AddMovieRequest addMovieRequest) {
	    AddMovieResponse response=new AddMovieResponse();
	    try {
	    	UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	// Check if the authenticated user has the "seller" role
	        //if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
	        	Optional<User> optionalUser=userRepository.findById(authenticatedUser.getId());
	        	
	        	if(optionalUser.isPresent()) {
	        		User user=optionalUser.get();
	        		
	        		Movies movies=new Movies();
	        		
	        		movies.setMovieName(addMovieRequest.getMovieName());
	        		movies.setReleaseDate(addMovieRequest.getReleaseDate());
	        		movies.setCast(addMovieRequest.getCast());
	        		movies.setCrew(addMovieRequest.getCrew());
	        		movies.setDescription(addMovieRequest.getDescription());
	        		movies.setRuntime(addMovieRequest.getRuntime());
	        		movies.setRating(addMovieRequest.getRating());
	        		
	        		movieRepository.save(movies);
	        		
	        		response.setMessage("Movie,Added Successfully");
	        		response.setStatus(true);
	        		
	        	}else {
	        		response.setMessage("User not found");
	        		response.setStatus(false);
	        	}
	        	
	        
//	    else {
//	        	response.setMessage("User doesn't have required Role");
//        		response.setStatus(false);
//	        }
	    	
	    	
	    	
	    }catch (Exception e) {
	    	response.setMessage("Error occured");
    		response.setStatus(false);
		}
	    
	    return response;
	}


	@Override
	public DeleteResponse deleteMovie(Long movieId) {
		DeleteResponse deleteResponse=new DeleteResponse();
		try {
			UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> optionalUser=userRepository.findById(authenticatedUser.getId());
        	
        	if(optionalUser.isPresent()) {
        		User user=optionalUser.get();
        		
        	Optional<Movies> movieOptional=movieRepository.findById(movieId);
        	
        	if(movieOptional.isPresent()) {
        	
        		Movies movies=movieOptional.get();        		
        		movieRepository.delete(movies);
        		
        		deleteResponse.setStatus("Success");
    			deleteResponse.setMessage("Movie deleted, Successfully");
        	}else {
        		deleteResponse.setStatus("failed");
    			deleteResponse.setMessage("Movie not found");
        	}
        		
        	}else {
        		deleteResponse.setStatus("failed");
    			deleteResponse.setMessage("User, Not found");
        	}
			
		}catch (Exception e) {
			deleteResponse.setStatus("failed");
			deleteResponse.setMessage("Error occured");
		}
		return deleteResponse;
	}


	@Override
	public ApiResponse addCinema(AddCinemaRequest addCinemaRequest) {
		ApiResponse apiResponse=new ApiResponse();
		try {
			UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> optionalUser=userRepository.findById(authenticatedUser.getId());
        	
        	if(optionalUser.isPresent()) {
        	    Cinemas cinemas=new Cinemas();
        	    
        	    cinemas.setCinemaName(addCinemaRequest.getCinemaName());
        	    cinemaRepository.save(cinemas);
        	    apiResponse.setSuccess(true);
        		apiResponse.setMessage("Cinema Added, Successfully");
        	    
        	}else {
        		apiResponse.setSuccess(false);
        		apiResponse.setMessage("User, Not found");
        		
        	}
			
		}catch (Exception e) {
			apiResponse.setSuccess(false);
    		apiResponse.setMessage("Error,Occured");
		}
		return apiResponse;
	}


	@Override
	public DeleteResponse deleteCinema(Long cinemaId) {
		DeleteResponse deleteResponse=new DeleteResponse();
		try {
			UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			if(userOptional.isPresent()) {
				Optional<Cinemas> cinemaOptional=cinemaRepository.findById(cinemaId);
				if(cinemaOptional.isPresent()) {
					Cinemas cinemas=cinemaOptional.get();
					
					cinemaRepository.delete(cinemas);
					
					deleteResponse.setStatus("true");
					deleteResponse.setMessage("Cinema Added, Successfully");
					
				}else {
					deleteResponse.setStatus("false");
					deleteResponse.setMessage("Cinema, Not found");
				}
			}else {
				deleteResponse.setStatus("false");
				deleteResponse.setMessage("User, Not found");
			}
			
		}catch(Exception e) {
			deleteResponse.setStatus("false");
			deleteResponse.setMessage("Error,Occured");
		}
		return deleteResponse;
	}

	@Override
	public Map<String, Object> addShows(AddShowRequest addShowRequest) {
		Map<String, Object> map=new HashMap<>();
		try {
			UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> userOptional=userRepository.findById(authenticatedUser.getId());
			if(userOptional.isPresent()) {
				Shows shows=new Shows();
				
				Optional<Cinemas> cinemasOptional=cinemaRepository.findByCinemaName(addShowRequest.getCinemaName());
				if(cinemasOptional.isPresent()) {
				Optional<Movies> movieOptional=movieRepository.findByMovieName(addShowRequest.getMovieName());
				  if(movieOptional.isPresent()) {
				shows.setShowTime(addShowRequest.getShowTime());
				shows.setCinemas(cinemasOptional.get());
				shows.setMovie(movieOptional.get());
				
				showRepository.save(shows);
				
				map.put("status", true);
				map.put("message", "show added successfully");
				  }else {
					  map.put("status", false);
					  map.put("message", "movie not found");
				  }
				}else {
					  map.put("status", false);
					  map.put("message", "cinema not found");
				}
			}else {
					map.put("status", false);
					map.put("message", "user not found");
			}
			
			
		}catch(Exception e) {
			  map.put("status", false);
			  map.put("message", "Error Occured");
		}
		return map;
	}


	@Override
	public Map<String, Object> deleteShows(Long id) {
		Map<String, Object> map=new HashMap<>();
		
		return map;
		
		
	}


	@Override
	public Map<String, Object> addPrice(AddPriceRequest addPriceRequest) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
	            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
	            if (userOptional.isPresent()) {
	                User user = userOptional.get();

	                // Check if the price already exists
	                Optional<Price> existingPriceOptional = priceRepository.findByPrice(addPriceRequest.getPrice());
	                if (existingPriceOptional.isPresent()) {
	                    map.put("status", false);
	                    map.put("message", "Price already exists");
	                } else {
	                    Price price = new Price();
	                    price.setPrice(addPriceRequest.getPrice());
	                    priceRepository.save(price);
	                    map.put("status", true);
	                    map.put("message", "Price added successfully");
	                }
	            } else {
	                map.put("status", false);
	                map.put("message", "User not found");
	            }
	        } else {
	            map.put("status", "failed");
	            map.put("message", "User doesn't have required role");
	        }
	    } catch (Exception e) {
	        map.put("status", false);
	        map.put("message", "Error occurred");
	    }
	    return map;
	}
	
	public Map<String, Object> addSeat(AddSeatRequest addSeatRequest) {
        Map<String, Object> map = new HashMap<>();
        
        // Check if priceId exists
        Optional<Price> priceOptional = priceRepository.findById(addSeatRequest.getPriceId());
        if (priceOptional.isEmpty()) {
            map.put("status", false);
            map.put("message", "Price with ID " + addSeatRequest.getPriceId() + " does not exist.");
            return map;
        }
        
        // Check if showId exists
        Optional<Shows> showOptional = showRepository.findById(addSeatRequest.getShowId());
        if (showOptional.isEmpty()) {
            map.put("status", false);
            map.put("message", "Show with ID " + addSeatRequest.getShowId() + " does not exist.");
            return map;
        }

        try {
            Seat seat = new Seat();
            seat.setRowNo(addSeatRequest.getRowNo());
            seat.setSeatNumber(addSeatRequest.getSeatNumber());
            seat.setBooked(addSeatRequest.isBooked());
            seat.setPrice(priceOptional.get());
            seat.setShows(showOptional.get());

            
             seatRepository.save(seat);

            map.put("status", true);
            map.put("message", "Seat added successfully");
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "Error occurred while adding seat: " + e.getMessage());
        }
        
        return map;
    }

	
}
