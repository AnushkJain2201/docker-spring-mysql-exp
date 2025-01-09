package com.epoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epoll.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
	
	Roles findByRoleName(String roleName);

}
