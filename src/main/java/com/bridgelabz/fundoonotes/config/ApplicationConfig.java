package com.bridgelabz.fundoonotes.config;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.util.UserToken;
@Configuration
public class ApplicationConfig {
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper getMapper()
	{
		ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration()
	        .setMatchingStrategy(MatchingStrategies.STRICT);
	    return modelMapper;
}
	@Bean
	public Response getResponse()
	{
		return new Response();
	}
	
	@Bean
	public UserToken getUserToken( ) {
		return new UserToken();
	}

}
