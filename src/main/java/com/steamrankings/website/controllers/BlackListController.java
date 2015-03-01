package com.steamrankings.website.controllers;

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

@Controller
public class BlackListController extends WebMvcConfigurerAdapter {

    @RequestMapping(value = "/blacklist", method = RequestMethod.GET)
    public String showIndex(SteamProfile profile, String error, String id, Model model) {
        if (id != null)
            return "redirect:/leaderboard";
        if (error != null) {
            System.out.println(error);
            // model.addAttribute("error", id);
            if (error.equals("3000")) {
                model.addAttribute("error", "Steam ID entered Invalid");
            } else if (error.equals("4000")) {
                model.addAttribute("error", "Steam ID is in the blacklist, please contact an admin");
            } else if (error.equals("2000")) {
                model.addAttribute("error", "Steam ID does not exist");
            } else if (error.equals("1000")) {
                model.addAttribute("error", "Bad Argument entered");
            } else {
                return "redirect:/leaderboard";
            }
            return "blacklist";
        }

        return "blacklist";

    }

    @RequestMapping(value = "/blacklist", method = RequestMethod.POST)
    public String addBlackList(SteamProfile steamProfile, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "blacklist";
        }
        try {
            String resp = Profiles.addBlackList(handleQuery(steamProfile.getUserQuery()), Application.client);
            System.out.println("Resopnse" + resp);
        } catch (Exception e) {
            if (e instanceof APIException) {
                return "redirect:/blacklist?error=" + e.getMessage();
            }
        }
        return "redirect:/blacklist?id=" + handleQuery(steamProfile.getUserQuery());
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
