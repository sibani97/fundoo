package com.bridgelabz.fundoonotes.util;

import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.ResponseToken;

public class StatusHelper {
	
	public static ResponseToken statusInfo(String statusMessage, int statusCode){
		ResponseToken response = new ResponseToken();
		
		response.setStatusCode(statusCode);
		
		response.setStatusMessage(statusMessage);
	
		return response;
	}
	
	public static ResponseToken tokenStatusInfo(String statusMessage,int statusCode,String token){
		ResponseToken tokenResponse = new ResponseToken();
		
		tokenResponse.setStatusMessage(statusMessage);
		tokenResponse.setStatusCode(statusCode);
		tokenResponse.setToken(token);

		return tokenResponse;
	}
	public static Response statusResponseInfo(String statusMessage, int statusCode) {
		Response response = new Response();

		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}
}
