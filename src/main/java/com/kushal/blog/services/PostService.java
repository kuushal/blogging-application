package com.kushal.blog.services;

import java.util.List;

import com.kushal.blog.entities.Post;
import com.kushal.blog.payloads.PostDto;
import com.kushal.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);

	PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize);
	
	List<PostDto> searchPosts(String keyword);
}
