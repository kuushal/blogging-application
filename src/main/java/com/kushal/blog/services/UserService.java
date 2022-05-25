package com.kushal.blog.services;

import java.util.List;

import com.kushal.blog.payloads.UserDto;

public interface UserService {
	
	UserDto registerUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userID);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
