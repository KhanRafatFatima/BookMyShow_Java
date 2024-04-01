package com.ebos.tables;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cinemas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    private String cinemaName;
    

    @ManyToMany
    @JoinTable(
        name = "cinema_movie",joinColumns = @JoinColumn(name = "cinema_id"),inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movies> movies;

    @OneToMany(mappedBy = "cinemas")
    private Set<Shows> shows;
    
//    @OneToMany(mappedBy = "cinemas")
//    private Set<Tickets> tickets;

    public Cinemas() {
    	
    }

	public Cinemas(String cinemaName, Set<Movies> movies) {
		super();
		this.cinemaName = cinemaName;
		this.movies = movies;
	}
    
    
}
