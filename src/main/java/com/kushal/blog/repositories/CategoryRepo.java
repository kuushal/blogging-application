package com.kushal.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.blog.entities.Category;
import com.kushal.blog.entities.Post;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
