package com.steamrankings.website.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;

@Controller
public class LeaderboardController {

    /*
    @ModelAttribute("rankentries")
    public List<RankEntryByAchievements> populateTable() {

        RankEntryByAchievements r1 = new RankEntryByAchievements(1, 1234, "swag", 500, "50", "Canada");
        RankEntryByAchievements r2 = new RankEntryByAchievements(2, 4321, "yolo", 501, "51", "USA");
        List<RankEntryByAchievements> curList = new ArrayList<RankEntryByAchievements>();
        curList.add(r1);
        curList.add(r2);

        return curList;
    }
    */

    @RequestMapping("/leaderboard")
    public String getLeaderboard(Model model) {
        List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByAchievementTotal(1, 10);
        model.addAttribute("rankentries", rankEntries);
        return "leaderboard";
    }
}
