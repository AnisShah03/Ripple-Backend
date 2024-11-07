package com.primus.ripple.service;

import org.springframework.http.ResponseEntity;

import com.primus.ripple.dto.ProfileDto;
import com.primus.ripple.dto.UserResponse;

public interface UserService {

	ResponseEntity<?> followUser(UserResponse response);

	ResponseEntity<?> unFollowUser(UserResponse response);

	ResponseEntity<?> getFollowers(String username);

	ResponseEntity<?> getFollowing(String username);

	ResponseEntity<?> updateProfile(ProfileDto profileDto);

	ResponseEntity<?> getProfile(String username);

}
