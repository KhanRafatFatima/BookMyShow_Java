package com.ebos.tables;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    private String showTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinemas cinemas;

    
    @OneToMany(mappedBy = "shows")
    private Set<Tickets> tickets;
    
    @OneToMany(mappedBy = "shows")
    private Set<Seat> seats;

    public Shows() {
    	
    }

	public Shows(String showTime) {
		super();
		this.showTime = showTime;
	}
    
    
}
