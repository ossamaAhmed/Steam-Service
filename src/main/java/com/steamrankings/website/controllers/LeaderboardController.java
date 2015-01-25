package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {

    @RequestMapping("/leaderboard")
    public String index() {
        return "leaderboard";
    }
}
