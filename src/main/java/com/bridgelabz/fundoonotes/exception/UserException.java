package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="User Invaild")
public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	int code;
	String msg;
	public UserException(int code, String msg) {
		super(msg);
		this.code = code;
		
	}
	public UserException(String msg) {
		super(msg);
		
	}
	
	
	

}
