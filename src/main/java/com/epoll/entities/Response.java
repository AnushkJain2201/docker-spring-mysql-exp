package com.epoll.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Response {
	
	
	private Integer userId;
	
	private Roles role;

	private String username;
	
	private String firstName;

	private String lastName;
	
	private String gender;
	


	private String email;
	
	private String alternateEmail;
	
	private String passwordHash;
	
	private String phoneNumber;
	
	private String aadharNumber;

	private String city;

	private String state;

	private String country;

	private String pincode;
	
	private LocalDate dateOfBirth;
	
	private String profilePicture;
	
	
	private LocalDateTime lastLogin;
	

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private String college;
	
	private String branch;
	
	private Integer passingYear;
	
	
	private String linkedin;
	
	private String instagram;
	
	private String twitter;
	
	private String facebook;

	private String marksheet;

}
