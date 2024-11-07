package com.primus.ripple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primus.ripple.dto.ProfileDto;
import com.primus.ripple.dto.UserResponse;
import com.primus.ripple.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// POST method to create a new follow user
	@PostMapping("/follow")
	public ResponseEntity<?> setFollow(@RequestBody UserResponse response) {
		return userService.followUser(response);
	}

	@PostMapping("/unfollow")
	public ResponseEntity<?> unFollow(@RequestBody UserResponse response) {
		return userService.unFollowUser(response);
	}

	@GetMapping("/followers/{username}")
	public ResponseEntity<?> getFollowers(@PathVariable String username) {
		return userService.getFollowers(username);
	}

	@GetMapping("/following/{username}")
	public ResponseEntity<?> getFollowing(@PathVariable String username) {
		return userService.getFollowing(username);
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> getProfile(@PathVariable String username) {
		return userService.getProfile(username);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateProfile(@RequestBody ProfileDto profileDto) {
		return userService.updateProfile(profileDto);
	}

}
