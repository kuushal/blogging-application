package com.kushal.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.blog.entities.Category;
import com.kushal.blog.entities.Post;
import com.kushal.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	Page<Post> findByUserId(Integer userId, Pageable p);

	List<Post> findByTitleContaining(String title);
}
