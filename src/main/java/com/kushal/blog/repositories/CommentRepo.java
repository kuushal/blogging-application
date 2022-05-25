package com.kushal.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
