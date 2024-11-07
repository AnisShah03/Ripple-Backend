package com.primus.ripple.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {

	private String username;
	private MultipartFile profile_image;
	private String bio;

}
