package com.ebos.tables;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String movieName;

    private LocalDate releaseDate;

    private String description;

    private String cast;

    private String crew;

    private String rating;

    private String runtime;

    @OneToMany(mappedBy = "movie")
    private Set<Shows> shows;

    @OneToMany(mappedBy = "movie")
    private Set<Offers> offers;
    
    @ManyToMany(mappedBy = "movies")
    private Set<Cinemas> cinemas;
    
//    @OneToMany(mappedBy = "movies")
//    private Set<Tickets> tickets;

    public Movies() {
    	
    }

	public Movies(String movieName, LocalDate releaseDate, String description, String cast, String crew, String rating,
			String runtime) {
		super();
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.description = description;
		this.cast = cast;
		this.crew = crew;
		this.rating = rating;
		this.runtime = runtime;
	}
    
    
    
}
