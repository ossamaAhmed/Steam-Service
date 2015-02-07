package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("message", profile.getSteamCommunityId());
        model.addAttribute("url", profile.getSteamCommunityUrl());
        return "profile";
    }
}