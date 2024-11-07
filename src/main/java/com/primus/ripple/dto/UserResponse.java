package com.primus.ripple.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String username;
	private String followUser;
	private String unfollowUser;

}
