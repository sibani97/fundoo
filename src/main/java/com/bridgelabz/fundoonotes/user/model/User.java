
package com.bridgelabz.fundoonotes.user.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.notes.model.Notes;



@Entity
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name="username")
	private String userName;
	@Column(name="email")
	private String emailId;
	@Column(name="password")
	private String password;

	@Column(name = "registeredDate")
	private LocalDate registeredDate;
	
	
	@Column(name = "isVerified")
	boolean isVarified;
	
	@Column(name = "updatedDate")
	private LocalDate updatedDate;
	@Column(name = "mobileNumber")
	@Pattern(regexp = "[0-9]{10}" , message = "enter your mobile number")
	@NotEmpty(message = "please provide your mobile number")
	private String mobileNumber;

	@JoinColumn(name="userId") 
	@OneToMany(cascade=CascadeType.ALL)
	List<Notes> notes;
	
	
	@OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="userId")
	List<Labels> lable;
	 
	
	//@JoinColumn(name="userId")
//	@OneToMany(cascade=CascadeType.ALL)
//	Set<Label> lable1;
//	public Set<Label> getLable1() {
//		return lable1;
//	}
//
//
//
//	public void setLable1(Set<Label> lable1) {
//		this.lable1 = lable1;
//	}



//	public List<Label> getLable() {
//		return lable;
//	}
//
//
//
//	public void setLable(List<Label> lable) {
//		this.lable = lable;
//	}



	

	public List<Notes> getNotes() {
		return notes;
	}



	public List<Labels> getLable() {
		return lable;
	}



	public void setLable(List<Labels> lable) {
		this.lable = lable;
	}



	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	



	



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", registeredDate=" + registeredDate + ", isVarified=" + isVarified + ", updatedDate=" + updatedDate
				+ ", mobileNumber=" + mobileNumber +  "]";
	}



	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isVarified() {
		return isVarified;
	}

	public void setVarified(boolean isVarified) {
		this.isVarified = isVarified;
	}

	
}
