package com.registration.login.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registration.login.models.UserRegistration;
import com.registration.login.models.UserRegistrationDto;

public interface UserRegistrationService extends UserDetailsService{

	//save user's registered data
	
	UserRegistration save(UserRegistrationDto registrationDto);

	UserRegistration findByEmail(String email);


}
