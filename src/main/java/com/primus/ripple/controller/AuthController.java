package com.primus.ripple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primus.ripple.entities.Users;
import com.primus.ripple.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Users user) {
		return service.registerUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Users user) {
		return service.loginUser(user);
	}

	@PostMapping("/resetpass")
	public ResponseEntity<?> resetPassword(@RequestBody Users user) {
		return service.resetPassword(user);
	}

}
