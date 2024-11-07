package com.primus.ripple.serviceImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.primus.ripple.Exception.UserNotFoundException;
import com.primus.ripple.dto.ProfileDto;
import com.primus.ripple.dto.UserResponse;
import com.primus.ripple.entities.Users;
import com.primus.ripple.repository.UserRepository;
import com.primus.ripple.responseStructure.ResponseStructure;
import com.primus.ripple.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> followUser(UserResponse response) {

		Optional<Users> userOpt = userRepository.findByUsername(response.getUsername());// me
		// this person is who i want to follow
		Optional<Users> followUserOpt = userRepository.findByUsername(response.getFollowUser());

		if (userOpt.isPresent() && followUserOpt.isPresent()) {
			Users user = userOpt.get();
			// adding targeted user to the followers list
			Set<Users> followersList = user.getFollowing();// try with set
			followersList.add(followUserOpt.get());
			userRepository.save(user);

			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("user added to the follwers list");
			responseStructure.setData("Now following " + followUserOpt.get().getUsername());
			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		}

		return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> unFollowUser(UserResponse response) {

		Optional<Users> user = userRepository.findByUsername(response.getUsername());

		// this person is who i want to unfollow
		Optional<Users> unFollowUser = userRepository.findByUsername(response.getUnfollowUser());

		if (user.isPresent() && unFollowUser.isPresent()) {

			Set<Users> followingList = user.get().getFollowers();

			followingList.remove(unFollowUser.get());

			userRepository.save(user.get());

			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("unfollowing");
			responseStructure.setData("Unfollowing " + unFollowUser.get().getUsername());

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);

		}
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData("something went wrong");

		return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);

	}

	@Override
	public ResponseEntity<?> getFollowers(String username) {
		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user is not found"));

		Set<Users> followersList = user.getFollowers(); // List of users who follow "User X"
		if (followersList != null) {
			ResponseStructure<Set<Users>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("followersList");
			responseStructure.setData(followersList); // Sending followers list in response
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("List not found");
			responseStructure.setData("no followers");
			return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<?> getFollowing(String username) {
		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user is not found"));

		Set<Users> followingList = user.getFollowing();
		if (followingList != null) {
			ResponseStructure<Set<Users>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("followingList");
			responseStructure.setData(followingList);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("List not found");
		responseStructure.setData("no following");
		return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> updateProfile(ProfileDto profileDto) {

		Users user = userRepository.findByUsername(profileDto.getUsername())
				.orElseThrow(() -> new UserNotFoundException("user not found"));

		MultipartFile file = profileDto.getProfile_image();
		try {
			if (file != null) {
				user.setProfile_url(file.getBytes());
			}
			if (profileDto.getBio() != null) {
				user.setBio(profileDto.getBio());
			}
			userRepository.save(user);
		} catch (IOException e) {
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setMessage("something went wrong");
			responseStructure.setData("try again later");
			return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		ResponseStructure<ProfileDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("profile updated successfully");
		responseStructure.setData(profileDto);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getProfile(String username) {

		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not found"));

		ProfileDto profileDto = new ProfileDto();
		BeanUtils.copyProperties(user, profileDto);
		ResponseStructure<ProfileDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("user profile details");
		responseStructure.setData(profileDto);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);

	}

}
