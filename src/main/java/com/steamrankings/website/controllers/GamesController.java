package com.steamrankings.website.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;

import com.steamrankings.website.Application;

@Controller
public class GamesController {

    @RequestMapping("/gameslist")
    public String getGamesLeaderboard(Model model) {
        List<SteamGame> rankEntries = null;
        try {
            rankEntries = Games.getSteamGames(Application.client);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*if (rankEntries != null) {
            for (int i = 0; i < rankEntries.size(); i++) {
                if (rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
                    String countryFlag = "/assets/images/country_flags/" + rankEntries.get(i).getCountryCode().toLowerCase() + ".png";
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                } else {
                    String countryFlag = "/assets/images/country_flags/_United Nations.png";
                    rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                            .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                }
            }
        }*/
        model.addAttribute("rankentries", rankEntries);
        return "gameslist";
    }

    // @RequestMapping("/games")
}
