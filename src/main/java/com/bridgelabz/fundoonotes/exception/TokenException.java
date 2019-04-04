package com.bridgelabz.fundoonotes.exception;

public class TokenException extends RuntimeException {
	int code;
	String msg;
	public TokenException(int code, String msg)
	 {
		 //super(msg);
		super(msg);
		 this.code =code;
}

}
