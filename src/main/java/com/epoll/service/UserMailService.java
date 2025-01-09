package com.epoll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class UserMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendEmail(String to, String subject, String body) {
		
		
		

		try {
			// Creating a helper to set up the email message
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true); // The second argument 'true' means HTML content

			// Send the email
			javaMailSender.send(message);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace(); 
			
			return false;
		}
	}

}
