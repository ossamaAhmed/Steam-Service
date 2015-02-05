package com.steamrankings.website.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;

@Controller
public class LeaderboardController {

    @RequestMapping("/leaderboard")
    public String getLeaderboard(Model model) {
//        model.addAttribute("rank1", "32");
//        model.addAttribute("name", "steve");
//        model.addAttribute("achivements", "33");
//        model.addAttribute("time", "453");
//        model.addAttribute("country", "Canada");
        
        RankEntryByAchievements r1 = new RankEntryByAchievements("eric", new Long(1234), "Canada", 500, 50);
        RankEntryByAchievements r2 = new RankEntryByAchievements("yolo", new Long(4321), "USA", 501, 51);

        List<RankEntryByAchievements> curList = new ArrayList<RankEntryByAchievements>();
        curList.add(r1);
        curList.add(r2);
        
        Leaderboards l = new Leaderboards(curList);
        
        List<RankEntryByAchievements> newList = l.getRanksByAchievementTotal(1, 2);
        
        for (int i = 0; i < newList.size(); i++) {
            model.addAttribute("rank"+(i+1), newList.get(i).getRankNumber());
            model.addAttribute("name"+(i+1), newList.get(i).getName());
            model.addAttribute("achievement"+(i+1), newList.get(i).getTotalNumberOfAchievements());
            model.addAttribute("time"+(i+1), newList.get(i).getAverageCompetionRate());
            model.addAttribute("country"+(i+1), newList.get(i).getCountry());
        }
        
    	return "leaderboard";
    }
}
