package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.steamrankings.website.model.SteamProfile;

@Controller
public class IndexController extends WebMvcConfigurerAdapter {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(SteamProfile profile, String error, Model model) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String redirect(SteamProfile profile) {
		return "redirect:/";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String checkPersonInfo(SteamProfile steamProfile,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "index";
		}
		
		return "redirect:/profile?id="
				+ handleQuery(steamProfile.getUserQuery());
	}

	/* Parse url otherwise, return text */
	private String handleQuery(String query) {
		String match = "steamcommunity.com/id/";
		int index = query.indexOf(match);
		if (index != -1) {
			index += match.length();
			return query.substring(index, query.length());
		}
		return query;
	}
}