package com.primus.ripple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primus.ripple.dto.PostDto;
import com.primus.ripple.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/{username}")
	public ResponseEntity<?> uploadPost(@RequestBody PostDto postDto) {
		return service.uploadPost(postDto);
	}

	@GetMapping("/{username}/{pageNumber}")
	public ResponseEntity<?> getMyPost(@PathVariable String username, @PathVariable Integer pageNumber) {
		return service.getMyPost(username, pageNumber);
	}
	
	

}
