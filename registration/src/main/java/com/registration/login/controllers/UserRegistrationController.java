package com.registration.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.login.models.UserRegistration;
import com.registration.login.models.UserRegistrationDto;
import com.registration.login.services.UserRegistrationService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userService;

	public UserRegistrationController(UserRegistrationService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@Validated @ModelAttribute("user")UserRegistrationDto registrationDto, BindingResult result ,Model model) {
		
		UserRegistration ExistingUser = userService.findByEmail(registrationDto.email);
		
		if(ExistingUser != null) {	
			result.rejectValue("email", null,"Email Already Exist!!!");	
		}
		
		if (!registrationDto.getPassword().equals(registrationDto.getConfirm_password())) {
			result.rejectValue("confirm_password",null, " Password and confirm-password fields don't match");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", registrationDto);
			return "/registration";
		}
		
		userService.save(registrationDto);
		return "redirect:/registration?success";//?success this connect with register.html page
		
	}
}
