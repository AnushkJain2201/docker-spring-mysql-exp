package com.epoll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epoll.entities.EpollUsers;
import com.epoll.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EpollUsers user = repo.findByEmail(email);
		
		if(user == null) throw new UsernameNotFoundException("No User Found");
		
		return User.builder().username(email)
				.password(user.getPasswordHash())
				.authorities(user.getRole().getRoleName())
				.build();
	}
	

}
