package com.testregistration.testregistration;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;

public class SuccessController  {
	@GetMapping("/")
	public String index(Principal principal){
		if(principal != null){
			return "redirect:/success";
		}
		return "index";
	}	
}