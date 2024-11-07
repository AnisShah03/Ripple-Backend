package com.primus.ripple.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primus.ripple.dto.PostDto;
import com.primus.ripple.entities.Posts;
import com.primus.ripple.entities.Users;
import com.primus.ripple.repository.PostRepository;
import com.primus.ripple.repository.UserRepository;
import com.primus.ripple.responseStructure.ResponseStructure;

@Repository
public class PostDao {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PostRepository postRepository;

	public ResponseEntity<?> uploadPost(PostDto postDto) {

		Optional<Users> user = repository.findByUsername(postDto.getUsername());

		if (!user.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User does not exist");
			rs.setData("User not found");
			return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
		}

		MultipartFile file = postDto.getPost_image();
		String caption = postDto.getCaption();

		Posts post = new Posts();
		try {
			post.setPost_image(file.getBytes());
			post.setCaption(caption);
			post.setUser(user.get());
			postRepository.save(post);

			ObjectMapper mapper = new ObjectMapper();
			PostDto postMapper = mapper.convertValue(mapper, PostDto.class);

			System.out.println(postMapper);// TODO check

			ResponseStructure<PostDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Post is successfully uploaded");
			rs.setData(postMapper);

			return new ResponseEntity<ResponseStructure<PostDto>>(rs, HttpStatus.CREATED);

		} catch (IOException e) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setMessage("Post upload failed");
			rs.setData("something went wrong");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<?> getPost(String username, Integer pageNumber) {
		Optional<Users> user = repository.findByUsername(username);

		if (user.isPresent()) {

			List<Posts> posts = user.get().getPosts();

			if (posts != null) {
				// pageable from org.springframework.data.domain
				Pageable postPage = PageRequest.of(pageNumber - 1, 5);// -1 because counting start from 1

				List<Posts> content = postRepository.findAll(postPage).getContent();
				ResponseStructure<List<Posts>> rs = new ResponseStructure<>();

				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("post fetching");
				rs.setData(content);
				// this constructor accepts response with a body and status code.
				return new ResponseEntity<ResponseStructure<List<Posts>>>(rs, HttpStatus.OK);
			} else {
				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.NO_CONTENT.value());
				rs.setMessage("no post");
				rs.setData("content is not available");
				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NO_CONTENT);

			}

		} else {

			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("somehting went wrong");
			rs.setData("User is not exist");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

		}

	}

}
