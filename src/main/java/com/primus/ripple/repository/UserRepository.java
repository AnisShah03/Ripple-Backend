package com.primus.ripple.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primus.ripple.entities.Users;
import java.util.List;


public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsernameAndPassword(String username, String password);

//	Optional<Users> findByUsernameOrPhone(String username, Long phone);

	Optional<Users> findByUsername(String username);

	Optional<Users> findByUsernameAndPhone(String username, Long phone);
	
	
	

}
