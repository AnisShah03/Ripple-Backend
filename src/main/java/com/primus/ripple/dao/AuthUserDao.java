package com.primus.ripple.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.primus.ripple.Exception.UserNotFoundException;
import com.primus.ripple.entities.Users;
import com.primus.ripple.repository.UserRepository;
import com.primus.ripple.responseStructure.ResponseStructure;

@Repository
public class AuthUserDao {

	@Autowired
	private UserRepository repository;

	public ResponseEntity<?> registerUser(Users user) {
		ResponseStructure<Users> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("user is saved sucessfully");
		responseStructure.setData(user);
		saveUser(user);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
	}

	public void saveUser(Users user) {
		repository.save(user);
	}

	public ResponseEntity<?> loginUser(Users user) {
		Optional<Users> userOptional = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (userOptional.isPresent()) {
			if (userOptional.get().isFirst()) {
				// go to the reset password
				ResponseStructure<String> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.RESET_CONTENT.value());
				responseStructure.setMessage("user first logged in");
				responseStructure.setData("going to reset password");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.RESET_CONTENT);
			} else {
				// go to the dashboard
				ResponseStructure<String> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("user is not first");
				responseStructure.setData("going to dashboard");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
			}
		} else {
			// go to the register page
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			responseStructure.setMessage("invalid password");
			responseStructure.setData("to login first register");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<?> resetPassword(Users user) {
		Optional<Users> userOptional = repository.findByUsername(user.getUsername());
		if (userOptional.isPresent()) {
			userOptional.get().setPassword(user.getPassword());// we want new password
			userOptional.get().setFirst(false);
			ResponseStructure<Users> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.RESET_CONTENT.value());
			responseStructure.setMessage("user resetting password");
			responseStructure.setData(userOptional.get());
			saveUser(userOptional.get());
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.RESET_CONTENT);
		}
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
		responseStructure.setMessage("something went wrong");
		responseStructure.setData("try again later with valid details");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.REQUEST_TIMEOUT);

	}

	public Optional<Users> getByUserName(String username) {
		// from here send response
		return repository.findByUsername(username);
	}

	public ResponseEntity<?> deleteAccount(Users user) {

		Users accUser = repository.findByUsername(user.getUsername())
				.orElseThrow(() -> new UserNotFoundException("User not found"));

		repository.deleteById(accUser.getId());
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("User Account is deleted");

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);

	}

}
