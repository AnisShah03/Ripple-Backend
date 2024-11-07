package com.primus.ripple.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.primus.ripple.responseStructure.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> catchUserNotFound(UserNotFoundException message) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("user is not found");
		rs.setData(null);
		return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	}

}
