package com.steamrankings.website.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;
import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.service.api.profiles.SteamProfile;

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
        SteamProfile profile = Profiles.getSteamUser(id);
        model.addAttribute("full_avatar_url", profile.getFullAvatarUrl());
        model.addAttribute("personal_name", profile.getPersonaName());
        model.addAttribute("message", profile.getSteamCommunityId());
        model.addAttribute("url", profile.getSteamCommunityUrl());

        return "profile";
    }

    @ModelAttribute("games")
    public List<SteamGame> populateGames() {
        SteamGame gameOne = new SteamGame(123, "icon", "http://logonoid.com/images/thumbs/cs-logo.png", "Counter Strike");
        SteamGame gameTwo = new SteamGame(1234, "icon2", "logourl2", "Counter Strike Source");

        List<SteamGame> curList = new ArrayList<SteamGame>();
        curList.add(gameOne);
        curList.add(gameTwo);

        return curList;
        
//        return null;
    }

//    @ModelAttribute("games")
//    public List<SteamGame> populateGames(String id) {     
//        List<SteamGame> games = Games.getPlayedSteamGames(id);
//        if (games.isEmpty()) {
//        	System.out.println("empty");
//        }
//        return games;
//    }
}