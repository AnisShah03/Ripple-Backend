package com.primus.ripple.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.primus.ripple.dao.AuthUserDao;
import com.primus.ripple.entities.Users;
import com.primus.ripple.service.AuthService;
import com.primus.ripple.utility.Utility;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthUserDao dao;

	@Autowired
	private JavaMailSender sender;

	public ResponseEntity<?> registerUser(Users user) {
		Optional<Users> userOptional = dao.getByUserName(user.getUsername());
		if (userOptional.isPresent()) {
			return ResponseEntity.status(409).body("User already exists.");
		} else {
			String password = Utility.generatePassword();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getMail());
			message.setSubject("Ripple App");
			message.setText("to reset account password this is your first time password :-" + password);// use textblock
																										// here
			sender.send(message);
			user.setPassword(password);
			return dao.registerUser(user);
		}
	}

	public ResponseEntity<?> loginUser(Users user) {
		return dao.loginUser(user);
	}

	public ResponseEntity<?> resetPassword(Users user) {
		return dao.resetPassword(user);
	}

	@Override
	public ResponseEntity<?> deleteAccount(Users user) {

		return dao.deleteAccount(user);
	}

}
