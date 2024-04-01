package com.ebos.Request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMovieRequest {
        	
	private String movieName;

    private LocalDate releaseDate;

    private String description;

    private String cast;

    private String crew;

    private String rating;

    private String runtime;
    
    public AddMovieRequest() {
    	
    }

}
