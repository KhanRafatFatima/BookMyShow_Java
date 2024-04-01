package com.ebos.tables;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    private Long price;

    @OneToMany(mappedBy = "price")
    private Set<Seat> seats;

    public Price() {
    	
    }

	public Price(Long price) {
		super();
		this.price = price;
	}
    
    
    
    
}
