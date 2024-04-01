package com.ebos.tables;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qrCode;
    
    private LocalDateTime bookingTime;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
//    @ManyToOne
//    @JoinColumn(name="cinema_id")
//    private Cinemas cinemas;
//    
//    @ManyToOne
//    @JoinColumn(name="movie_id")
//    private Movies movies;
    
    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seats;
    
    @ManyToOne
    @JoinColumn(name="show_id")
    private Shows shows;

    public Tickets() {
    	
    }
    
    
}
