package com.primus.ripple.service;

import org.springframework.http.ResponseEntity;

import com.primus.ripple.entities.Users;

public interface AuthService {
	ResponseEntity<?> registerUser(Users user);

	ResponseEntity<?> loginUser(Users user);

	ResponseEntity<?> resetPassword(Users user);

	ResponseEntity<?> deleteAccount(Users user);
}
