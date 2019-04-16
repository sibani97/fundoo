package com.bridgelabz.fundoonotes.user.service;



import java.io.UnsupportedEncodingException;
//import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import javax.mail.MessagingException;

import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.ResponseToken;
import com.bridgelabz.fundoonotes.user.dto.LoginDto;
import com.bridgelabz.fundoonotes.user.dto.UserDto;
import com.bridgelabz.fundoonotes.user.model.User;
public interface UserService  {

	ResponseToken onRegister(UserDto userDto)throws UserException, MessagingException, UnsupportedEncodingException ;
	
	
	
	ResponseToken onLogin(LoginDto  loginDto)throws UserException, UnsupportedEncodingException;
    
   
    Response validateEmailId(String token) throws UserException;
    
   
    Response forgetPassword(String emailId) throws UserException, UnsupportedEncodingException, MessagingException;
   
    Response resetPaswords(String token,String password) throws UserException;
    
    
   

	ResponseToken authentication(Optional<User> user, String password) throws UnsupportedEncodingException, UserException;
	



}

