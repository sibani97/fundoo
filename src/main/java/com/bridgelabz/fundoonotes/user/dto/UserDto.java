package com.bridgelabz.fundoonotes.user.dto;



import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {
	public UserDto() {
		super();
		
	}
	@Column(name="username")
	private String userName;
	@Column(name="email")
	private String emailId;
	@Column(name="password")
	private String password;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name = "mobileNumber")
	@Pattern(regexp = "[0-9]{10}" , message = "enter your mobile number")
	@NotEmpty(message = "please provide your mobile number")
	private String mobileNumber;
	

	
	
	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", emailId=" + emailId + ", password=" + password + ", mobileNumber="
				+ mobileNumber + "]";
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
