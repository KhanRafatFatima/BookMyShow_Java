package com.ebos.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSeatRequest {
	
	private Long seatId;

    private Long showId; 
    
    private Long priceId; 

    private String rowNo;

    private int seatNumber;

    private boolean booked;
}
