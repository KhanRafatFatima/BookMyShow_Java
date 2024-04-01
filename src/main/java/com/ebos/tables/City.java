package com.ebos.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    private String cityName;

    private String state;

    public City() {
    	
    }

	public City(String cityName,String state) {
		super();
		this.cityName = cityName;
		this.state = state;
	}
    
    
    
}
