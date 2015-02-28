package com.steamrankings.website.controllers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.website.Application;
import com.steamrankings.website.model.SteamProfile;
import com.steamrankings.website.Application;

@Controller
public class BlackListController extends WebMvcConfigurerAdapter {
	
	@RequestMapping(value = "/blacklist", method = RequestMethod.GET)
	public String showIndex(SteamProfile profile, String id, Model model) {
		if (id != null) {
			//model.addAttribute("error", id);
			return "redirect:/leaderboard";
			
		}
		
		return "blacklist";
	}


	@RequestMapping(value = "/blacklist", method = RequestMethod.POST)
	public String addBlackList(SteamProfile steamProfile,
			BindingResult bindingResult) throws JSONException, ClientProtocolException, APIException, IOException{

		if (bindingResult.hasErrors()) {
			return "blacklist";
		}
		Profiles.addBlackList(handleQuery(steamProfile.getUserQuery()));
		System.out.println("Switching to leaderboard");
		return "redirect:/blacklist?id="
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
