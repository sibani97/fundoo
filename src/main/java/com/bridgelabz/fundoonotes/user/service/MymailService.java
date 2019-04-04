package com.bridgelabz.fundoonotes.user.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.util.UserToken;
@Service
public class MymailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@GetMapping(value="/sendemail")
	 public void sendMail(String to, String subject, String text)
	 {
		 System.out.println("to-->"+to);
		 System.out.println("subject"+subject);
		 SimpleMailMessage message=new SimpleMailMessage();
//		 message.setFrom("sibani003@gmail.com");
		 message.setReplyTo(to);
		 message.setTo(to);
		 message.setText(text);
		 message.setSubject(subject);
		 
		 System.out.println("Message : "+message);
		 javaMailSender.send(message);
		 System.out.println("mail successfully send");
		 
	 }
	public String getUrl(Long id) throws IllegalArgumentException, UnsupportedEncodingException
	{	
		 UserToken userToken = new UserToken();
	
		return "http://localhost:8080/user/"+userToken.generateToken(id);
	}

}
