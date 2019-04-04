package com.bridgelabz.fundoonotes.util;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

public class UserToken {
	
private  static String Token;

public  String generateToken(long id) throws IllegalArgumentException, UnsupportedEncodingException {
	Token="Sibani";
	Algorithm algorithm = Algorithm.HMAC256(Token);
	String token=JWT.create().withClaim("ID", id).sign(algorithm);
	return token;		
}

//token decode
public  long tokenVerify(String token){
	Token="Sibani";

	long userid;
	//here verify the given token's algorithm
	Verification verification = null;
	try {
		verification = JWT.require(Algorithm.HMAC256(UserToken.Token));
	} catch (IllegalArgumentException | UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JWTVerifier jwtverifier=verification.build();
	DecodedJWT decodedjwt=jwtverifier.verify(token);
	Claim claim=decodedjwt.getClaim("ID");
	userid=claim.asLong();	
	System.out.println(userid);
	return userid;
}



}
