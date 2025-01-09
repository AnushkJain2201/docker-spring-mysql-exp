package com.epoll.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PersonalDetails {

	private Integer userId;

	private String username;

	private String firstName;

	private String lastName;

	private String gender;
	
	private LocalDate dateOfBirth;

	private String phoneNumber;

	private String aadharNumber;
	
	private String alternateEmail;

}
