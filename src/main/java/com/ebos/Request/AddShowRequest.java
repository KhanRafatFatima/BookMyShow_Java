package com.ebos.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddShowRequest {
 
	private String showTime;
	
	private String cinemaName;
	
	private String movieName;
}
