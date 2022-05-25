package com.kushal.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty(message = "Name should not be empty.")
	@Size(min = 4,message = "Username must be of minimum of 4 characters.")
	private String name;
	
	@Email(message = "Email address is not valid.")
	@NotEmpty(message="Email address should not be empty.")
	private String email;
	
	@NotEmpty(message="Password should not be empty.")
	@Size(min = 4,message="Password must be minimum 4 characters")
	private String password;
	
	@NotEmpty(message = "About should not be empty")
	private String about;

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}
	
	private Set<RoleDto> roles=new HashSet<>();

}
