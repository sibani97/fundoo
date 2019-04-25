package com.bridgelabz.fundoonotes.labels.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Labels {
	
	
	@Override
	public String toString() {
		return "Labels [labelId=" + labelId + ", labelName=" + labelName + ", userId=" + userId + ", notes=" + notes
				+ "]";
	}

	@Id
	@Column(name = "labelId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long labelId;
	private String labelName;
	private long userId;
	
	



	
	
	
	

public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

@ManyToMany
@JoinTable(name = "note_label",
joinColumns =  @JoinColumn(name="label_Id", referencedColumnName="labelId"),
inverseJoinColumns =  @JoinColumn(name = "note_Id",referencedColumnName="noteId") )
private Set<Notes> notes;
	
	
	public Set<Notes> getNotes() {
	return notes;
}
public void setNotes(Set<Notes> notes) {
	this.notes = notes;
}
	public long getLabelId() {
		return labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	
	
	

}
