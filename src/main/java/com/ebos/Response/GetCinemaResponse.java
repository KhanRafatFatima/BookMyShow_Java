package com.ebos.Response;

import java.util.List;

import com.ebos.tables.Cinemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCinemaResponse {
	private Boolean status;
    private String message;
    List<Cinemas> list;
    
    
    
}
