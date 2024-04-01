package com.ebos.Response;

import java.util.List;

import com.ebos.tables.Movies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMovieResponse {
	 	private Boolean status;
	    private String message;
	    List<Movies> list;
	    
	    
	   public  GetMovieResponse() {
	   }

	   public GetMovieResponse(Boolean status, String message) {
	        this.status = status;
	        this.message = message;
	    }
}
