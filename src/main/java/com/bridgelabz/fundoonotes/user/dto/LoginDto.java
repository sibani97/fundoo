package com.bridgelabz.fundoonotes.user.dto;

import javax.persistence.Column;

public class LoginDto {
	
	@Override
	public String toString() {
		return "LoginDto [emailId=" + emailId + ", password=" + password + "]";
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Column(name="email")
	private String emailId;
	
	
	@Column(name="password")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
