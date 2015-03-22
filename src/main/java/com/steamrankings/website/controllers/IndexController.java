package com.steamrankings.website.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.steamrankings.service.api.news.News;
import com.steamrankings.service.api.news.SteamNews;
import com.steamrankings.website.Application;
import com.steamrankings.website.model.SteamProfile;

@Controller
public class IndexController extends WebMvcConfigurerAdapter {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(SteamProfile profile, String error, Model model) {
		if(error !=null)
		{
			if (error.equals("3000")) {
				model.addAttribute("error", "Steam ID entered Invalid");
			}
			else if(error.equals("4000"))
			{
				model.addAttribute("error", "Steam ID is in the blacklist, please contact an admin");
			}
			else if(error.equals("2000"))
			{
				model.addAttribute("error", "Steam ID does not exist or is private");
			}
			else if(error.equals("1000"))
			{
				model.addAttribute("error", "Bad Argument entered");
			}
			else 
			{
				model.addAttribute("error", "A Steam ID consists of only numbers or letters. No spaces");
			}
		}
		
		ArrayList<SteamNews> steamNews = new ArrayList<SteamNews>();
		try {
			steamNews = News.getSteamNews(Application.client);
			model.addAttribute("steamnews", steamNews.get(0));
		} catch (Exception e) {
			// should not occur
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