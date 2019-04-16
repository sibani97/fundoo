package com.bridgelabz.fundoonotes.user.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.util.StatusHelper;
@RestControllerAdvice
public class ExceptionGlobal extends ResponseEntityExceptionHandler{
	@ExceptionHandler(UserException.class)
	public final ResponseEntity<Response> userHendlerException(UserException userexc,WebRequest request)
	{
		Response statusinfo=new Response();
		statusinfo=StatusHelper.statusResponseInfo(userexc.getMessage(),-100);
		return new ResponseEntity<Response>(statusinfo,HttpStatus.OK);
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response> parmanentExceptionHandler(Exception exception,WebRequest request)
	{
		Response statusinfo=new Response();
		statusinfo=StatusHelper.statusResponseInfo(exception.getMessage(),-200);
		return new ResponseEntity<Response>(statusinfo,HttpStatus.OK);
	}

}
