package com.registration.login.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registration.login.models.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

	//This is method ,Retrieve the information according to the user email from the database
	
	UserRegistration findByEmail(String email);
}
