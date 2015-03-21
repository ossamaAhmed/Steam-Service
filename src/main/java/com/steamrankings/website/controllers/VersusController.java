package com.steamrankings.website.controllers;

import java.util.ArrayList;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.service.api.profiles.SteamProfile;
import com.steamrankings.service.api.profiles.VersusResult;
import com.steamrankings.website.Application;

@Controller
public class VersusController {

    @RequestMapping("/versus")
    public String getProfile(String id1, String id2, Model model) throws JSONException {
        // We add the error message to our model
        ArrayList<VersusResult> results = null;
        SteamProfile profile1 = null;
        SteamProfile profile2 = null;
        
        try {
            results = Profiles.compareSteamUsers(id1, id2, Application.client);
            profile1 = Profiles.getSteamUser(id1, Application.client);
            profile2 = Profiles.getSteamUser(id2, Application.client);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "redirect:/?error=" + e.getMessage();
            }
            if (e instanceof IllegalArgumentException) {
                return "redirect:/?error=" + e.getMessage();
            }
        }

        model.addAttribute("results", results);
        model.addAttribute("user_1_name", profile1.getPersonaName());
        model.addAttribute("user_2_name", profile2.getPersonaName());
        model.addAttribute("user_1_avatar", profile1.getFullAvatarUrl());
        model.addAttribute("user_2_avatar", profile2.getFullAvatarUrl());        
        
        if (results == null || profile1 == null || profile2 == null) {
            return "index";
        }

        return "versus";
    }
    
}