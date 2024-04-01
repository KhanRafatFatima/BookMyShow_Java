package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows shows;
    
    @ManyToOne
    @JoinColumn(name="price_id")
    private Price price;
    
    @OneToOne(mappedBy = "seats")
    private Tickets tickets;

    
    private String rowNo;
    
    private int seatNumber;
    
    private boolean booked;

    
}
