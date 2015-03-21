package com.steamrankings.website.controllers;

import java.util.List;

import org.json.JSONException;
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

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String getProfile(String id, Model model) throws JSONException {
        // We add the error message to our model
        SteamProfile profile = null;
        try {
            System.out.println(id);
            profile = Profiles.getSteamUser(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "redirect:/?error=" + e.getMessage();
            }
            if (e instanceof IllegalArgumentException) {
                return "redirect:/?error=" + e.getMessage();
            }
        }

        if (profile == null) {
            return "index";
        }

        model.addAttribute("id", profile.getSteamId64());
        model.addAttribute("full_avatar_url", profile.getFullAvatarUrl());
        model.addAttribute("personal_name", profile.getPersonaName());
        model.addAttribute("url", profile.getSteamCommunityUrl());
        if (profile.getCountryCode() != null && Application.steam_countries.has(profile.getCountryCode())) {
            JSONObject countryData = Application.steam_countries.getJSONObject(profile.getCountryCode());
            model.addAttribute("country", countryData.getString("name"));
            model.addAttribute("country_code", profile.getCountryCode());
            System.out.println(profile.getCountryCode().toLowerCase() + ".png");
            model.addAttribute("country_flag", "/assets/images/country_flags/128/" + profile.getCountryCode().toLowerCase() + ".png");
        } else {
            model.addAttribute("United Nations", "");
            model.addAttribute("country_code", "UN");
            model.addAttribute("country_flag", "/assets/images/country_flags/un.png");
        }

        List<SteamGame> games = null;
        try {
            games = Games.getPlayedSteamGames(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }
        if (games == null) {
            return "index";
        }

        model.addAttribute("games", games);

        List<GameAchievement> achievements = null;
        try {
            achievements = Achievements.getUnlockedAchievements(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }

        model.addAttribute("achievements", achievements);
        
        List<SteamProfile> friends = null;
        try {
            friends = Profiles.getSteamFriends(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }
        
        if(friends.isEmpty()) {
            model.addAttribute("no_friends_msg", "This user has no friends");
        }
        
        model.addAttribute("friends", friends);

        return "profile";
    }
    
    
    @RequestMapping("/update")
    public String updateProfile(String id, Model model) {
        // We add the error message to our model
        SteamProfile profile = null;
        try {
            System.out.println(id);
            profile = Profiles.updateUser(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "redirect:/?error=" + e.getMessage();
            }
            if (e instanceof IllegalArgumentException) {
                return "redirect:/?error=" + e.getMessage();
            }
        }

        if (profile == null) {
            return "index";
        }

        model.addAttribute("full_avatar_url", profile.getFullAvatarUrl());
        model.addAttribute("user_id", profile.getSteamId64());
        model.addAttribute("personal_name", profile.getPersonaName());
        model.addAttribute("url", profile.getSteamCommunityUrl());
        if (profile.getCountryCode() != null && Application.steam_countries.has(profile.getCountryCode())) {
            JSONObject countryData = Application.steam_countries.getJSONObject(profile.getCountryCode());
            model.addAttribute("country", countryData.getString("name"));
            System.out.println(profile.getCountryCode().toLowerCase() + ".png");
            model.addAttribute("country_flag", "/assets/images/country_flags/128/" + profile.getCountryCode().toLowerCase() + ".png");
        } else {
            model.addAttribute("country", "");
            model.addAttribute("country_flag", "/assets/images/country_flags/128/un.png");
        }

        List<SteamGame> games = null;
        try {
            games = Games.getPlayedSteamGames(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }
        if (games == null) {
            return "index";
        }

        model.addAttribute("games", games);

        List<GameAchievement> achievements = null;
        try {
            achievements = Achievements.getUnlockedAchievements(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }

        model.addAttribute("achievements", achievements);
        
        List<SteamProfile> friends = null;
        try {
            friends = Profiles.getSteamFriends(id, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "/?error=" + e.getMessage();
            }
        }
        
        if(friends.isEmpty()) {
            model.addAttribute("no_friends_msg", "This user has no friends");
        }
        
        model.addAttribute("friends", friends);

        return "profile";
    }
    
}