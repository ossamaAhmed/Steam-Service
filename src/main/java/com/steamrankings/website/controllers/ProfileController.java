package com.steamrankings.website.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.achievements.Achievements;
import com.steamrankings.service.api.achievements.GameAchievement;
import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;
import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.service.api.profiles.SteamProfile;
import com.steamrankings.website.Application;
import com.steamrankings.service.api.APIException;

// HTTP requests handled by the controller
// You identify controllers in Spring by using the annotation @Controller
@Controller
public class ProfileController {

    // Good Spring MVC tutorial at
    // https://spring.io/guides/gs/serving-web-content/

    // This annotation sets that the HTTP requests of /profile are handled by
    // the getProfile method
    @RequestMapping("/profile")
    public String getProfile(String id, Model model) {
        // We add the error message to our model
    	SteamProfile profile = null;
    	try {
    		profile = Profiles.getSteamUser(id);
    	}
        catch (Exception e) {
        	if (e instanceof APIException) {
        		return "error?=" + e.getMessage();
        	}
        }
    	
        if (profile == null) {
            return "index";
        }
        model.addAttribute("full_avatar_url", profile.getFullAvatarUrl());
        model.addAttribute("personal_name", profile.getPersonaName());
        model.addAttribute("url", profile.getSteamCommunityUrl());
        if (profile.getCountryCode() != null && Application.steam_countries.has(profile.getCountryCode())) {
            JSONObject countryData = Application.steam_countries.getJSONObject(profile.getCountryCode());
            model.addAttribute("country", countryData.getString("name"));
            System.out.println(profile.getCountryCode().toLowerCase() + ".png");
            model.addAttribute("country_flag", "/assets/images/country_flags/" + profile.getCountryCode().toLowerCase() + ".png");
        } else {
            model.addAttribute("country", "");
            model.addAttribute("country_flag", "/assets/images/country_flags/_United Nations.png");
        }
        List<SteamGame> games = null;
		try {
			games = Games.getPlayedSteamGames(id);
		} catch (Exception e) {
			if (e instanceof APIException) {
        		return "error?=" + e.getMessage();
        	}
        }
		if (games == null) {
			return "index";
		}
        model.addAttribute("games", games);

        List<GameAchievement> achievements = Achievements.getUnlockedAchievements(id);
        model.addAttribute("achievements", achievements);

        return "profile";
    }
}