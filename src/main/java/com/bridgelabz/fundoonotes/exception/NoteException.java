package com.bridgelabz.fundoonotes.exception;

public class NoteException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code ;
	String msg;
	public NoteException(int code, String msg) {
		super(msg);
		this.code = code;
		
	}

}
