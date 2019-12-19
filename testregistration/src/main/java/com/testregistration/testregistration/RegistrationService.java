package com.testregistration.testregistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService implements UserDetailsService {
	
	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RegistrationBase userFindByUsername = registrationRepository.findByUsername(username);
		RegistrationBase userFindByName = registrationRepository.findByName(username);
		
		if(userFindByUsername != null){
			return userFindByUsername;
		}

		if(userFindByName != null){
			return userFindByName;
		}

		return null;
	}
}
