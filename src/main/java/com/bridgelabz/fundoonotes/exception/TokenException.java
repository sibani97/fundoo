package com.bridgelabz.fundoonotes.exception;

public class TokenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String msg;
	public TokenException(int code, String msg)
	 {
		 //super(msg);
		super(msg);
		 this.code =code;
}

}
