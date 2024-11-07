package com.primus.ripple.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	@Email(message = "email is not valid")
	private String mail;

	@Column(unique = true)
	@Size(min = 5, max = 20, message = "username length must be between 7 to 20")
	private String username;

//	@Size(min = 8, max = 20, message = "password char is must greater than 8 and less than 12")
	private String password;

	private Long phone;

	@Column(nullable = true)
	private byte[] profile_url;

	@Column(nullable = true)
	private String bio;

	private boolean isFirst = true;

	@ManyToMany
	@JoinTable(name = "user_following", // Specify a table name like "user_following" for better clarity
			joinColumns = @JoinColumn(name = "user_id"), // The user who is following ex. me
			inverseJoinColumns = @JoinColumn(name = "following_user_id") // The user being followed
	)
	@JsonIgnore
	private Set<Users> following;

	// List of users who follow this user
	@ManyToMany(mappedBy = "following")
	@JsonIgnore
	private Set<Users> followers;

	/*
	 * orphanRemoval: Using orphanRemoval = true ensures that when you remove a Post
	 * from a User, it’s deleted from the database. check with false
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
	private List<Posts> posts = new ArrayList<>();

}
