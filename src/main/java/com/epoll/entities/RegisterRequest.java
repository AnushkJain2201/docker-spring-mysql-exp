package com.epoll.entities;

import lombok.Data;

@Data
public class RegisterRequest {
	



	private String username;
	
	private String email;
	
	private String password;
	
	private String firstName;

	private String lastName;

	private Integer graduationYear;


	private String city;

	private String state;

	private String country;

	private String pincode;
	
	
	


}
