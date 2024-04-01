package com.ebos.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMovieResponse {

		private Boolean status;
	    private String message;
	    
	    
	   public AddMovieResponse() {}

	   public AddMovieResponse(Boolean status, String message) {
	        this.status = status;
	        this.message = message;
	    }
	   
	   
}
