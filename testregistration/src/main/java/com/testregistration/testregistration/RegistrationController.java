package com.testregistration.testregistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	  @GetMapping("/registration")
	  public String registration(){
	     return "registration";
	  }
	  
	  @PostMapping("/registration")
	  public String addUser(String name, String username, String password) {
		 if (name.length() == 0 || username.length() == 0 || password.length() == 0)
			 return "/registration";
		  RegistrationBase reg = new RegistrationBase();
		  reg.setName(name);
		  reg.setUsername(username);
		  reg.setPassword(passwordEncoder.encode(password));
		  registrationRepository.save(reg);

	     return "redirect:/login";
	  }
}
