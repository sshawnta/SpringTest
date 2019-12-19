package com.testregistration.testregistration;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogonControlelr {

	@GetMapping("/success")
	public String notes(Principal principal, Model model)
	{
		return "success";
	}
}
