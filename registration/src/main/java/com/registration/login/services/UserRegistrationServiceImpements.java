package com.registration.login.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.login.models.Login;
import com.registration.login.models.UserRegistration;
import com.registration.login.models.UserRegistrationDto;

//annotate the service classes
@Service
public class UserRegistrationServiceImpements implements UserRegistrationService{

	//@Autowired is field base Injection for UserRegistrationRepository interface but here we are using constructor base injection

	private UserRegistrationRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserRegistrationServiceImpements(UserRegistrationRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	//create save method to save data to the database
	
	@Override
	public UserRegistration save(UserRegistrationDto registrationDto) {
		UserRegistration user = new UserRegistration(registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()),passwordEncoder.encode(registrationDto.getConfirm_password()),		
				Arrays.asList(new Login("ROLE_USER")));
		
		
			return userRepository.save(user);	
	}	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRegistration user = userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		//create user object
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	//authority
	private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Login> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public UserRegistration findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
}
