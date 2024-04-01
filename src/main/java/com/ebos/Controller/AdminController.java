package com.ebos.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebos.Request.AddCinemaRequest;
import com.ebos.Request.AddMovieRequest;
import com.ebos.Request.AddPriceRequest;
import com.ebos.Request.AddSeatRequest;
import com.ebos.Request.AddShowRequest;
import com.ebos.Response.AddMovieResponse;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Service.AdminService;
import com.ebos.Service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
    
	@Autowired
	private AdminService adminService;
	
	 @PostMapping("/addMovie")
	    public ResponseEntity<AddMovieResponse> addMovie(@RequestBody AddMovieRequest addMovieRequest) {
	        AddMovieResponse response = adminService.addMovie(addMovieRequest);

	        return ResponseEntity.ok(response);
	    }
	 
	 @DeleteMapping("/deleteMovie/{movieId}")
	    public ResponseEntity<DeleteResponse> deleteMovie(@PathVariable Long movieId) {
	        DeleteResponse response = adminService.deleteMovie(movieId);

	        return ResponseEntity.ok(response);
	    }
	 
	 @PostMapping("/addCinema")
	    public ResponseEntity<ApiResponse> addCinema(@RequestBody AddCinemaRequest addCinemaRequest) {
		 ApiResponse response = adminService.addCinema(addCinemaRequest);

	        return ResponseEntity.ok(response);
	    }
	 
	 @DeleteMapping("/deleteCinema/{cinemaId}")
	    public ResponseEntity<DeleteResponse> deleteCinema(@PathVariable Long cinemaId) {
	        DeleteResponse response = adminService.deleteCinema(cinemaId);

	        return ResponseEntity.ok(response);
        }
	 
	 @PostMapping("/addShow")
	    public ResponseEntity<Map<String, Object>> addShow(@RequestBody AddShowRequest addShowRequest) {
	        Map<String, Object> response = adminService.addShows(addShowRequest);
	        if ((boolean) response.get("status")) {
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.badRequest().body(response);
	        }
	    }
	 
	 @PostMapping("/addPrice")
	 public ResponseEntity<Map<String, Object>> addPrice(@RequestBody AddPriceRequest addPriceRequest){
		 Map<String, Object> response=adminService.addPrice(addPriceRequest);
		 if ((boolean) response.get("status")) {
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.badRequest().body(response);
	        }
	 }
	 
	 @PostMapping("/addSeats")
	    public ResponseEntity<Map<String, Object>> addSeat(@RequestBody AddSeatRequest addSeatRequest) {
	        Map<String, Object> response = adminService.addSeat(addSeatRequest);
	        
	        if ((boolean) response.get("status")) {
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	    }
	
	
	@GetMapping("/getAllUsers")
	public SetListResponse findAll() {
		return userService.findAll();
	}
	
	@DeleteMapping("/deleteuser")
    public ResponseEntity<?> deleteUser() {
		
		try {
			return new ResponseEntity<DeleteResponse>(userService.deleteUser(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DeleteResponse>(userService.deleteUser(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
