package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;	
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private String offerTitle;

    private String content;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    public Offers() {
    	
    }

	public Offers(String offerTitle, String content) {
		super();
		this.offerTitle = offerTitle;
		this.content = content;
	}
    
    
}
