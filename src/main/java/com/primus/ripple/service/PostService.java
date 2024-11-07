package com.primus.ripple.service;

import org.springframework.http.ResponseEntity;

import com.primus.ripple.dto.PostDto;

public interface PostService {
	ResponseEntity<?> uploadPost(PostDto postDto);
	ResponseEntity<?> getMyPost(String username, Integer pageNumber);
	
}
