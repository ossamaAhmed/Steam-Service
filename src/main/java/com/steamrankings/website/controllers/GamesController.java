package com.steamrankings.website.controllers;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.leaderboards.RankEntryByTotalPlayTime;

public class GamesController {
	
	@RequestMapping("/games")
	public String getGamesLeaderboard(Model model) {
		List<RankEntryByTotalPlayTime> rankEntries = Games.getRanksByTotalPlayTime(1, 10);
		for(int i = 0; i < rankEntries.size(); i++) {
			if(rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
				  String countryFlag = "/assets/images/country_flags/" + rankEntries.get(i).getCountryCode().toLowerCase() + ".png";
				  rankEntries.set(i, new RankEntryByTotalPlayTime(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(),
						  rankEntries.get(i).getName(),rankEntries.get(i).getTotalPlayTime(), countryFlag));
			} else {
                String countryFlag = "/assets/images/country_flags/_United Nations.png";
                rankEntries.set(i, new RankEntryByTotalPlayTime(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(),
						  rankEntries.get(i).getName(),rankEntries.get(i).getTotalPlayTime(), countryFlag));
			}
		}
		model.addAttribute("rankEntries", rankEntries);
		return "games";
	}
	
	//@RequestMapping("/games")
}
