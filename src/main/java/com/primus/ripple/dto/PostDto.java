package com.primus.ripple.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostDto {

	private String username;
	private MultipartFile post_image;
	private String caption;

}
