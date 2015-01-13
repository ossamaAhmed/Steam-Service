package com.steamrankings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// HTTP requests handled by the controller
// You identify controllers in Spring by using the annotation @Controller
@Controller
public class ProfileController {
	// This annotation sets that the HTTP requests of /profile are handled by the getProfile method
	@RequestMapping("/profile")
	public String getProfile(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "profile";
	}
}