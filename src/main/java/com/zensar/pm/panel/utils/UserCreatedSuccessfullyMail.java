package com.zensar.pm.panel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.exceptions.MailNotSentException;

@Service
public class UserCreatedSuccessfullyMail {
	@Autowired
	JavaMailSender mailSender;

	@org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
	private String sender;

	public void userCreatedSuccessfully(String name, String emailId, String password) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			String message = "Hi " + name + ",\n\n" + "Your account has been created successfully.\n\n"
					+ "Your UserName: " + name + "\n Your Password: " + password
					+ "\nPlease login using these credentials. "
					+ "\nOnce you login you can change your password. \nhttp://localhost:3000/";
			String subject = "Admin - Panel Management";

			simpleMailMessage.setFrom(sender);
			simpleMailMessage.setTo(emailId);
			simpleMailMessage.setText(message);
			simpleMailMessage.setSubject(subject);

			mailSender.send(simpleMailMessage);
			// return true;

		} catch (MailException e) {
			throw new MailNotSentException("Something went wrong while sending the mail");
		}
	}
}
