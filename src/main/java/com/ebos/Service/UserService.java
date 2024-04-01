package com.ebos.Service;
import java.util.Map;

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

public interface UserService {
	    
	    GetMovieResponse findAllMovies();
	    
	    GetCinemaResponse findAllCinemas();
	    
	    public Map<String, Object> findAllShows();
	    
	    public Map<String, Object> addReview(AddMovieReview addMovieReview);
	    
	    public Map<String,Object> bookTicket(BookTicketRequest bookTicketRequest);
	    
	    public Map<String, Object> deleteReview(String movieName);
	    
	   public Map<String, Object> getReviews(String movieName);
	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	 	SetListResponse findAll();
	 	
	    ApiResponse save(SignUpRequest signUpRequest);
	 
	    DeleteResponse deleteUser();
	    
	    UpdateResponse updateUser(SignUpRequest signUpRequest);
	    
        public GetUserResponse getUserData();

}
