package com.epoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epoll.entities.EpollUsers;

public interface UsersRepository extends JpaRepository<EpollUsers, Integer> {
	
	EpollUsers findByEmail(String email);

}
