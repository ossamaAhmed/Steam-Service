package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {

    @RequestMapping("/leaderboard")
    public String getLeaderboard(Model model) {
        model.addAttribute("rank1", "32");
        model.addAttribute("name", "steve");
        model.addAttribute("achivements", "33");
        model.addAttribute("time", "453");
        model.addAttribute("country", "Canada");
       
    	
    	return "leaderboard";
    }
}
