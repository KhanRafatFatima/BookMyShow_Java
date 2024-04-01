package com.ebos.ServiceImplimentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebos.Request.AddMovieReview;
import com.ebos.Request.BookTicketRequest;
import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;
import com.ebos.Response.GetCinemaResponse;
import com.ebos.Response.GetMovieResponse;
import com.ebos.Response.GetUserResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Response.UpdateResponse;
import com.ebos.Service.UserService;
import com.ebos.repository.CinemaRepository;
import com.ebos.repository.MovieRepository;
import com.ebos.repository.MovieReviewRepository;
import com.ebos.repository.ShowRepository;
import com.ebos.repository.UserRepository;
import com.ebos.security.UserPrincipal;
import com.ebos.tables.Cinemas;
import com.ebos.tables.Movie_Reviews;
import com.ebos.tables.Movies;
import com.ebos.tables.Shows;
import com.ebos.tables.Tickets;
import com.ebos.tables.User;

@Service
public class UserServiceImpls implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private MovieReviewRepository movieReviewRepository;
	
	@Autowired
	private ShowRepository showRepository;

	
	
	@Override
	public GetMovieResponse findAllMovies() {
		GetMovieResponse getMovieResponse=new GetMovieResponse();
		try {
		List<Movies> list=movieRepository.findAll();
		getMovieResponse.setList(list);
		getMovieResponse.setMessage("fetched,Successfully");
		getMovieResponse.setStatus(true);
		}catch (Exception e) {
			getMovieResponse.setMessage("Error,Occured");
			getMovieResponse.setStatus(false);
		}
	    return getMovieResponse;
	}
	
	@Override
	public GetCinemaResponse findAllCinemas() {
		GetCinemaResponse getCinemaResponse=new GetCinemaResponse();
		try {
		List<Cinemas> list=cinemaRepository.findAll();
		getCinemaResponse.setList(list);
		getCinemaResponse.setMessage("fetched,Successfully");
		getCinemaResponse.setStatus(true);
		}catch (Exception e) {
			getCinemaResponse.setMessage("Error,Occured");
			getCinemaResponse.setStatus(false);
		}
		return getCinemaResponse;
	}
	
	@Override
	public Map<String, Object> getReviews(String movieName) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        // Find the movie by its name
	        Optional<Movies> movieOptional = movieRepository.findByMovieName(movieName);
	        if (movieOptional.isPresent()) {
	            Movies movie = movieOptional.get();
	            
	            List<Movie_Reviews> reviews = movieReviewRepository.findByMovie(movie);
	            
	            
	            List<Map<String, Object>> reviewList = new ArrayList<>();
	            for (Movie_Reviews review : reviews) {
	                Map<String, Object> reviewMap = new HashMap<>();
	                reviewMap.put("title", review.getTitle());
	                reviewMap.put("content", review.getContent());
	                reviewMap.put("user", review.getUser().getUsername());
	                reviewList.add(reviewMap);
	            }
	            
	            
	            map.put("status", "success");
	            map.put("message", "Reviews retrieved successfully");
	            map.put("reviews", reviewList);
	        } else {
	            map.put("status", "failed");
	            map.put("message", "Movie not found");
	        }
	    } catch (Exception e) {
	        map.put("status", "failed");
	        map.put("message", "Error occurred: " + e.getMessage());
	    }
	    return map;
	}

	
	
	@Override
	public Map<String, Object> addReview(AddMovieReview addMovieReview) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (authenticatedUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("USER"))) {
	            Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
	            if (userOptional.isPresent()) {
	                User user = userOptional.get();
	                Optional<Movies> movieOptional = movieRepository.findByMovieName(addMovieReview.getMovieName());

	                if (movieOptional.isPresent()) {
	                    Movie_Reviews movieReview = new Movie_Reviews();
	                    movieReview.setTitle(addMovieReview.getTitle());
	                    movieReview.setContent(addMovieReview.getContent());
	                    movieReview.setUser(user);
	                    movieReview.setMovie(movieOptional.get());
	                    
	                    movieReviewRepository.save(movieReview);
	                    
	                    map.put("status", "success");
	                    map.put("message", "Review added successfully");
	                } else {
	                    map.put("status", "failed");
	                    map.put("message", "Movie not found");
	                }
	            } else {
	                map.put("status", "failed");
	                map.put("message", "User not found");
	            }
	        } else {
	            map.put("status", "failed");
	            map.put("message", "User doesn't have required role");
	        }
	    } catch (Exception e) {
	        map.put("status", "failed");
	        map.put("message", "Error occurred: " + e.getMessage());
	    }
	    return map;
	}
	
	@Override
	public Map<String, Object> findAllShows() {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();
        try {
        	
        	List<Shows> showsList=showRepository.findAll();
        	
        	for(Shows shows:showsList) {
        		Map<String, Object> tempMap=new HashMap<>();
        	tempMap.put("showTime", shows.getShowTime());
        	tempMap.put("Cinemas", shows.getCinemas());
        	tempMap.put("Movie", shows.getMovie());
        	//tempMap.put("Price", shows.getPrice());
        	
        	list.add(tempMap);
        	}
        	
        	map.put("shows", list);
        	map.put("status",true);
        	map.put("message","fetched All the shows");
        	
        	
        }catch (Exception e) {
        	map.put("status", false);
        	map.put("message", "error occured");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> deleteReview(String movieName) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = authenticatedUser.getId();
            
            Optional<Movie_Reviews> reviewOptional = movieReviewRepository.findByMovie_MovieNameAndUser_UserId(movieName, userId);
            if (reviewOptional.isPresent()) {
                Movie_Reviews review = reviewOptional.get();
                movieReviewRepository.delete(review);
                map.put("status", "success");
                map.put("message", "Your review for the movie '" + movieName + "' has been deleted successfully");
            } else {
                map.put("status", "failed");
                map.put("message", "You haven't reviewed the movie '" + movieName + "'");
            }
        } catch (Exception e) {
            map.put("status", "failed");
            map.put("message", "Error occurred: " + e.getMessage());
        }
        return map;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public Map<String, Object> deleteReview(Long movieId) {
//		Map<String, Object> map=new HashMap<>();
//		try {
//			 UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			 Optional<User> userOptional=userRepository.findById(movieId);
//			 
//			 if(userOptional.isPresent()) {
//				  User user=userOptional.get();
//				  
//				  
//			 }else {
//				 
//			 }
//			 
//			 
//			
//		}catch (Exception e) {
//		}
//		return map;
//	}


//	@Override
//	public Map<String, Object> getReviews(String movieName) {
//	    Map<String, Object> map = new HashMap<>();
//	    try {
//	        UserPrincipal authenticatedUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	        Optional<User> userOptional = userRepository.findById(authenticatedUser.getId());
//
//	        if (userOptional.isEmpty()) {
//	            map.put("status", "failed");
//	            map.put("message", "User not found");
//	            return map;
//	        }
//
//	        Optional<Movies> movieOptional = movieRepository.findByMovieName(movieName);
//	        if (movieOptional.isEmpty()) {
//	            map.put("status", "failed");
//	            map.put("message", "Movie not found");
//	            return map;
//	        }
//
//	        Movies movie = movieOptional.get();
//	        List<Movie_Reviews> reviews = movieReviewRepository.findByMovie(movie);
//
//	        List<Map<String, Object>> reviewList = new ArrayList<>();
//	        for (Movie_Reviews review : reviews) {
//	            Map<String, Object> reviewMap = new HashMap<>();
//	            reviewMap.put("title", review.getTitle());
//	            reviewMap.put("content", review.getContent());
//	            reviewMap.put("user", review.getUser().getUsername());
//	            reviewList.add(reviewMap);
//	        }
//
//	        map.put("status", "success");
//	        map.put("message", "Reviews fetched successfully");
//	        map.put("reviews", reviewList);
//	    } catch (Exception e) {
//	        map.put("status", "failed");
//	        map.put("message", "Error occurred: " + e.getMessage());
//	    }
//	    return map;
//	}



	
	
	
	
	
	@Override
	public SetListResponse findAll() {
		SetListResponse setListResponse = new SetListResponse();
		List<User> list = userRepository.findAll();
		setListResponse.setMessage("Success");
		setListResponse.setStatus("True");
		setListResponse.setList(list);
		
		return setListResponse;
	}
	
	
	
	
	
	
	
	
	
      public GetUserResponse getUserData() {	
		GetUserResponse  userResponse = new GetUserResponse();
		try {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> user2 = userRepository.findById(user.getId());
			
			User user3 = user2.get();
			userResponse.setUser(user3);
			userResponse.setSuccess(true);
			userResponse.setMessage("success");
			
			return userResponse;
			
		}catch (Exception e) {
			return userResponse;
		}

	}

	

	@Override
	public ApiResponse save(SignUpRequest theUser) {
		ApiResponse apiResponse = new ApiResponse();
		User user = new User();
		userRepository.save(user);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("success");
		return apiResponse;
	}
	
	public DeleteResponse deleteUser() {
		DeleteResponse deleteResponse=new DeleteResponse();
		
		try {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> user2 = userRepository.findById(user.getId());
			
			User user3 = user2.get();
			
			userRepository.delete(user3);
			
			deleteResponse.setMessage("success");
			deleteResponse.setStatus("true");
			return deleteResponse;
		} catch (Exception e) {
			deleteResponse.setMessage("Failed to update");
			deleteResponse.setStatus("false");
			return deleteResponse;
		}

	}
	@Override
	public UpdateResponse updateUser(SignUpRequest signUpRequest) {
		UpdateResponse userInfoResponse = new UpdateResponse();
		try {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Optional<User> user2 = userRepository.findById(user.getId());
			
			User user3 = user2.get();
			
			user3.setEmail(signUpRequest.getEmail());
			user3.setName(signUpRequest.getName());
			user3.setUsername(signUpRequest.getUsername());
			user3.setPassword(signUpRequest.getPassword());
			
			userRepository.save(user3);
			
			userInfoResponse.setMessage("success");
			userInfoResponse.setSuccess(true);
			return userInfoResponse;
		} catch (Exception e) {
			userInfoResponse.setMessage("Failed to update");
			userInfoResponse.setSuccess(false);
			return userInfoResponse;
		}
	}

	@Override
	public Map<String, Object> bookTicket(BookTicketRequest bookTicketRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

	

	
}
	