package com.kushal.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
