package com.epoll.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "epoll_users")
public class EpollUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
	private Roles role;

	@Column(name = "username")
	private String username;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private String gender;
	


	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "alternate_email", unique = true)
	private String alternateEmail;
	
	@Column(name = "password_hash")
	private String passwordHash;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "profile_picture")
	private String profilePicture;
	
	
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "college")
	private String college;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "passing_year")
	private Integer passingYear;
	
	@Column(name = "12th_Marksheet")
	private String marksheet;
	
	
	@Column(name = "linkedin")
	private String linkedin;
	
	@Column(name = "instagram")
	private String instagram;
	
	@Column(name = "twitter")
	private String twitter;
	
	@Column(name = "facebook")
	private String facebook;

	

	
	
	

	// Constructors, Getters, Setters, and toString() method

}
