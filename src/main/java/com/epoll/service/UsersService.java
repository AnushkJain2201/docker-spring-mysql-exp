package com.epoll.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epoll.entities.EpollUsers;
import com.epoll.entities.Response;
import com.epoll.entities.Roles;
import com.epoll.repository.RolesRepository;
import com.epoll.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private UserMailService mailService;

	@Autowired
	private PasswordEncoder pwdEncoder;

	public EpollUsers findByEmail(String email) {

		return usersRepo.findByEmail(email);

	}

	public Response registerUser(EpollUsers newUser) {



		Roles savedRole = rolesRepo.findByRoleName("ROLE_USER");
		if (savedRole == null) throw new IllegalArgumentException("Role Not Found");

		newUser.setRole(savedRole);
		EpollUsers savedUser = usersRepo.save(newUser);
		Response response = new Response();
		BeanUtils.copyProperties(savedUser, response);

		return response;

	}

	public int forgotPassword(String email) {

		
		
		EpollUsers user = usersRepo.findByEmail(email);

		if (user == null) return 0;

		String randomPassword = generateRandomPassword(8);
		
		user.setPasswordHash(pwdEncoder.encode(randomPassword));
		
		usersRepo.save(user);
		
		String subject = "Your New Password - Epoll";
		String body = "<html><body>" + "<h2>Hello, "+user.getFirstName()+"</h2>"
				+ "<p>Your password has been successfully reset. Here is your new password:</p>" + "<h3>"
				+ randomPassword + "</h3>"
				+ "<p>Please keep your password safe and change it if you feel necessary.</p>"
				+ "<p>Best regards,<br/>The Epoll Support Team</p>" + "</body></html>";

		
		boolean sendEmail = mailService.sendEmail(email, subject, body);
		
		if(sendEmail) return 1;
		else return 2;
	}

	public String generateRandomPassword(int length) {

		final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();
		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(ALPHANUMERIC.length());
			password.append(ALPHANUMERIC.charAt(index));
		}

		return password.toString();

	}
	
	public  EpollUsers findUserById(Integer id) {
		
		Optional<EpollUsers> findById = usersRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}
	
	public EpollUsers updateUser(EpollUsers editedUser) {
		
		
		EpollUsers savedUser = usersRepo.save(editedUser);
		
		return savedUser;
	}

}
