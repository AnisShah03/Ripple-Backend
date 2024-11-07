package com.primus.ripple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primus.ripple.entities.Posts;

public interface PostRepository extends JpaRepository<Posts, Integer> {

}
