package com.bridgelabz.fundoonotes.exception;

public class LabelException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String message;
	public LabelException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	

}
