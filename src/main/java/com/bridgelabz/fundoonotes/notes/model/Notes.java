package com.bridgelabz.fundoonotes.notes.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.user.model.User;



@Entity

public class Notes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long noteId;
	String title;
	String description;
	boolean isPin;
	boolean isTrash;
	boolean archive;
	LocalDate createDate;
	LocalDate lastUpdateDate;
//	
//	@JoinColumn(name="mynote_id")
//	@ManyToMany(cascade=CascadeType.ALL)
//	User user;
	

	@JoinColumn(name="userId")
	@ManyToOne(cascade=CascadeType.ALL)
	User user;
	
	
	
	
//	@JoinColumn(name="mylabelId")
//	@ManyToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="mylabelId")
	
	
//	@ManyToMany(mappedBy="note", cascade=CascadeType.ALL)
//	List<Label>label;
	
//	public List<Label> getLabel() {
//		return label;
//	}
//	public void setLabel(List<Label> label) {
//		this.label = label;
//	}
	
	
	
	
	
	
	
	@ManyToMany(mappedBy="notes")
	Set<Labels> label;
	
	
	
	
	
	
	
	


	public Set<Labels> getLabel() {
		return label;
	}
	public void setLabel(Set<Labels> label) {
		this.label = label;
	}
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPin() {
		return isPin;
	}
	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Notes [noteId=" + noteId + ", title=" + title + ", description=" + description + ", isPin=" + isPin
				+ ", isTrash=" + isTrash + ", archive=" + archive + ", createDate=" + createDate + ", lastUpdateDate="
				+ lastUpdateDate + ", user=" + user +  "]";
	}
	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
	

}
