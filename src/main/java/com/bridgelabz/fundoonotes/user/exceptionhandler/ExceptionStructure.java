package com.bridgelabz.fundoonotes.user.exceptionhandler;

import java.time.LocalDateTime;

public class ExceptionStructure {
private LocalDateTime date;
private String message;
private String details;
public ExceptionStructure(LocalDateTime date, String message, String details) {
	super();
	this.date = date;
	this.message = message;
	this.details = details;
}

}
