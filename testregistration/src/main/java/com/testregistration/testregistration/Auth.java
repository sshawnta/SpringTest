package com.testregistration.testregistration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Auth implements AuthenticationProvider {

	 @Autowired
	 private RegistrationService registrationService;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		 String username = authentication.getName();
	     String password = (String) authentication.getCredentials();

	     RegistrationBase reg = (RegistrationBase) registrationService.loadUserByUsername(username);

	     if(reg != null && (reg.getUsername().equals(username) || reg.getName().equals(username)))
	     {	    	 
	    	if(!passwordEncoder.matches(password, reg.getPassword()))
	        {
	           throw new BadCredentialsException("Wrong password");
	        }

	        Collection<? extends GrantedAuthority> authorities = reg.getAuthorities();

	        return new UsernamePasswordAuthenticationToken(reg, password, authorities);
	     }
	     else
	        throw new BadCredentialsException("Username not found");
	 }
	 
	 public boolean supports(Class<?> arg){
		 return true;
	 }
}

