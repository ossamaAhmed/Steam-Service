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

    @ModelAttribute("rankentries")
    public List<RankEntryByAchievements> populateTable() {
        RankEntryByAchievements r1 = new RankEntryByAchievements("swag", new Long(1234), "Canada", 500, 50);
        RankEntryByAchievements r2 = new RankEntryByAchievements("yolo", new Long(4321), "USA", 501, 51);
        r1.setRankNumber(1);
        r2.setRankNumber(2);
        List<RankEntryByAchievements> curList = new ArrayList<RankEntryByAchievements>();
        curList.add(r1);
        curList.add(r2);
        
        Leaderboards l = new Leaderboards(curList);
        
        List<RankEntryByAchievements> newList = l.getRanksByAchievementTotal(1, 2);
        return newList;
    }

    @RequestMapping("/leaderboard")
    public String getLeaderboard(Model model) {
    	return "leaderboard";
    }
}
