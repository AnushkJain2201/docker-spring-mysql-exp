package com.epoll.controller;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epoll.entities.Education;
import com.epoll.entities.EpollUsers;
import com.epoll.entities.ForgotPassword;
import com.epoll.entities.LoginRequest;
import com.epoll.entities.PersonalDetails;
import com.epoll.entities.ProfilePicture;
import com.epoll.entities.RegisterRequest;
import com.epoll.entities.Response;
import com.epoll.entities.SocialMediaLinks;
import com.epoll.entities.UserAddress;
import com.epoll.service.UsersService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private UsersService userService;

	@PostMapping(value = "login")
	public ResponseEntity<Response> login(@RequestBody LoginRequest request) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		Authentication authenticate = authManager.authenticate(token);

		if (authenticate.isAuthenticated()) {

			Response response = new Response();
			EpollUsers user = userService.findByEmail(request.getEmail());
			user.setLastLogin(LocalDateTime.now());
			
			EpollUsers loginUpdatedUser = userService.updateUser(user);

			BeanUtils.copyProperties(loginUpdatedUser, response);
			
			
			

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PostMapping(value = "register")
	public ResponseEntity<Response> register(@RequestBody RegisterRequest registerBody) {

		EpollUsers user = userService.findByEmail(registerBody.getEmail());

		if (user != null)
			return ResponseEntity.badRequest().body(null);

		try {
			EpollUsers newUser = new EpollUsers();

			BeanUtils.copyProperties(registerBody, newUser);

			String encode = pwdEncoder.encode(registerBody.getPassword());
			newUser.setPasswordHash(encode);

			Response response = userService.registerUser(newUser);

			return new ResponseEntity<Response>(response, HttpStatus.CREATED);

		} catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@PostMapping(value = "forgotpwd")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPassword emailBody) {

		int result = userService.forgotPassword(emailBody.getEmail());

		if (result == 0)
			return new ResponseEntity<String>("Email not Registred", HttpStatus.BAD_REQUEST);
		else if (result == 2)
			return new ResponseEntity<String>("Email not deliverd", HttpStatus.CONFLICT);
		else
			return new ResponseEntity<String>("Password sent to email", HttpStatus.OK);

	}

	@GetMapping(value = "profile/{id}")
	public ResponseEntity<EpollUsers> fetchProfile(@PathVariable Integer id) {

		try {
			EpollUsers user = userService.findUserById(id);
			
			return new ResponseEntity<EpollUsers>(user, HttpStatus.OK);
			
		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	
	@PutMapping(value = "personalDetails")
	public ResponseEntity<Response> updatePersonalDetails(@RequestBody PersonalDetails personalDetails) {

		try {
			EpollUsers user = userService.findUserById(personalDetails.getUserId());

			BeanUtils.copyProperties(personalDetails, user);
			

			EpollUsers updatedUser = userService.updateUser(user);
			Response response = new Response();

			BeanUtils.copyProperties(updatedUser, response);

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	@PutMapping(value = "profilePic")
	public ResponseEntity<Response> updateProfilePicture(@RequestBody ProfilePicture profilePicture) {

		try {
			EpollUsers user = userService.findUserById(profilePicture.getUserId());

			BeanUtils.copyProperties(profilePicture, user);
			

			EpollUsers updatedUser = userService.updateUser(user);
			Response response = new Response();

			BeanUtils.copyProperties(updatedUser, response);

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	@PutMapping(value = "address")
	public ResponseEntity<Response> updateAddress(@RequestBody UserAddress address) {

		try {
			EpollUsers user = userService.findUserById(address.getUserId());

			BeanUtils.copyProperties(address, user);
			

			EpollUsers updatedUser = userService.updateUser(user);
			Response response = new Response();

			BeanUtils.copyProperties(updatedUser, response);

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	@PutMapping(value = "education")
	public ResponseEntity<Response> updateEducation(@RequestBody Education education) {

		try {
			EpollUsers user = userService.findUserById(education.getUserId());

			BeanUtils.copyProperties(education, user);
			

			EpollUsers updatedUser = userService.updateUser(user);
			Response response = new Response();

			BeanUtils.copyProperties(updatedUser, response);

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	@PutMapping(value = "mediaLinks")
	public ResponseEntity<Response> updateSocialMediaDetails(@RequestBody SocialMediaLinks socialMediaLinks) {

		try {
			EpollUsers user = userService.findUserById(socialMediaLinks.getUserId());

			BeanUtils.copyProperties(socialMediaLinks, user);
			

			EpollUsers updatedUser = userService.updateUser(user);
			Response response = new Response();

			BeanUtils.copyProperties(updatedUser, response);

			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (UsernameNotFoundException e) {
			System.out.println("No User Found Exception Occured");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	
	
	

}
