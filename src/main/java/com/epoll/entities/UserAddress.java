package com.epoll.entities;

import lombok.Data;
@Data
public class UserAddress {
	
	private Integer userId;
	
	private String city;

	private String state;

	private String country;

	private String pincode;
	

}
