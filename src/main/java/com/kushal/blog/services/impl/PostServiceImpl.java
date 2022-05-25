package com.kushal.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kushal.blog.entities.Category;
import com.kushal.blog.entities.Post;
import com.kushal.blog.entities.User;
import com.kushal.blog.exceptions.ResourceNotFoundException;
import com.kushal.blog.payloads.PostDto;
import com.kushal.blog.payloads.PostResponse;
import com.kushal.blog.repositories.CategoryRepo;
import com.kushal.blog.repositories.PostRepo;
import com.kushal.blog.repositories.UserRepo;
import com.kushal.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PostRepo postRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = this.dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post updatedPost = this.postRepo.save(post);

		return this.postToDto(updatedPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post postToUpdate = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		postToUpdate.setTitle(postDto.getTitle());
		postToUpdate.setContent(postDto.getContent());
		postToUpdate.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(postToUpdate);

		return this.postToDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.deleteById(postId);

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> allPosts = pagePosts.getContent();

		List<PostDto> allPostDto = allPosts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(allPostDto);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.postToDto(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> allPosts = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
		return allPosts;
	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> userPosts = this.postRepo.findByUserId(userId, p);

		List<Post> allPostForUserId = userPosts.getContent();
		List<PostDto> allPostDtoForUserId = allPostForUserId.stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDtoForUserId);
		postResponse.setPageNumber(userPosts.getNumber());
		postResponse.setPageSize(userPosts.getSize());
		postResponse.setTotalPages(userPosts.getTotalPages());
		postResponse.setTotalElements(userPosts.getTotalElements());
		postResponse.setLastPage(userPosts.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> postsByTitle = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtoByTitle = postsByTitle.stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());
		
		
		return postDtoByTitle;
	}

	public Post dtoToPost(PostDto postDto) {
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}

	public PostDto postToDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

}
