package com.primus.ripple.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.primus.ripple.dao.PostDao;
import com.primus.ripple.dto.PostDto;
import com.primus.ripple.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;

	public ResponseEntity<?> uploadPost(PostDto postDto) {
		return dao.uploadPost(postDto);
	}

	public ResponseEntity<?> getMyPost(String username, Integer pageNumber) {

		return dao.getPost(username, pageNumber);
	}

}
