package com.ebos.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebos.Request.AddMovieReview;
import com.ebos.Request.SignUpRequest;
import com.ebos.Response.ApiResponse;
import com.ebos.Response.DeleteResponse;
import com.ebos.Response.GetCinemaResponse;
import com.ebos.Response.GetMovieResponse;
import com.ebos.Response.GetUserResponse;
import com.ebos.Response.SetListResponse;
import com.ebos.Response.UpdateResponse;
import com.ebos.Service.UserService;
import com.ebos.tables.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/getMovies")
	public ResponseEntity<GetMovieResponse> getAllMovies() {
		GetMovieResponse response=userService.findAllMovies();

		return ResponseEntity.ok(response);
		
	}
	
	 @GetMapping("/getCinemas")
	    public ResponseEntity<GetCinemaResponse> findAllCinemas() {
	        GetCinemaResponse response = userService.findAllCinemas();

	        return ResponseEntity.ok(response);
	    }
	 
	 @GetMapping("/getShows")
	    public Map<String, Object> getAllShows() {
	        return userService.findAllShows();
	    }
	 
	 @PostMapping("/addReview")
	    public ResponseEntity<Map<String, Object>> addReview(@RequestBody AddMovieReview addMovieReview) {
	        Map<String, Object> response = userService.addReview(addMovieReview);
	        return ResponseEntity.ok(response);
	    }
	 
	 @GetMapping("/reviews/{movieName}")
	    public Map<String, Object> getReviewsByMovieName(@PathVariable String movieName) {
	        return userService.getReviews(movieName);
	    }
	 
	 @DeleteMapping("/deleteReview/{movieName}")
	    public Map<String, Object> deleteReview(
	            @PathVariable String movieName
	    ) {
	        return userService.deleteReview(movieName);
	    }
	 
//	 @GetMapping("/reviews/{movieName}")
//	    public ResponseEntity<Map<String, Object>> getMovieReviews(@PathVariable String movieName) {
//	        Map<String, Object> response = userService.getReviews(movieName);
//	        HttpStatus status = HttpStatus.OK;
//
//	        if (response.containsKey("status") && response.get("status").equals("failed")) {
//	            status = HttpStatus.BAD_REQUEST;
//	        }
//
//	        return new ResponseEntity<>(response, status);
//	    }
	 
	 
	
	@GetMapping("/get")
	public ResponseEntity<?> getUserData() {
		
		try {
			return new ResponseEntity<GetUserResponse>(userService.getUserData(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<GetUserResponse>(userService.getUserData(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/addUser")
	public ApiResponse addUser(@RequestBody SignUpRequest user) {
		return userService.save(user);

	}

	@PutMapping("/updateUserInfo")
	public ResponseEntity<?> updateUserInfo(@RequestBody SignUpRequest signUpRequest) {
		
		try {
			return new ResponseEntity<UpdateResponse>(userService.updateUser(signUpRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UpdateResponse>(userService.updateUser(signUpRequest),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}


