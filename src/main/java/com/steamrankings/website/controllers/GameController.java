package com.steamrankings.website.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;
import com.steamrankings.website.Application;

@Controller
public class GameController {

    @RequestMapping("/game")
    public String getGame(String id, Model model) throws JSONException {

        SteamGame game = null;

        try {
            game = Games.getSteamGame(Integer.parseInt(id), Application.client);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        model.addAttribute("game_logo_Url", game.getLogoUrl());
        model.addAttribute("game_name", game.getName());
        // model.addAttribute("url", profile.getSteamCommunityUrl());

        /*
         * List<RankEntryByAchievements> rankEntries = null; try { rankEntries =
         * Leaderboards.getRanksByGame(game.getAppId(), 1, 10,
         * Application.client); } catch (Exception e) { if (e instanceof
         * APIException) { return "/?error=" + e.getMessage(); } }
         * 
         * if (rankEntries != null) { for (int i = 0; i < rankEntries.size();
         * i++) { if (rankEntries.get(i).getCountryCode() != null &&
         * !rankEntries.get(i).getCountryCode().equals("")) { String countryFlag
         * = "/assets/images/country_flags/" +
         * rankEntries.get(i).getCountryCode().toLowerCase() + ".png";
         * rankEntries.set(i, new
         * RankEntryByAchievements(rankEntries.get(i).getRankNumber(),
         * rankEntries.get(i).getId64(), rankEntries.get(i).getName(),
         * rankEntries.get(i) .getAchievementsTotal(),
         * rankEntries.get(i).getCompletionRate(),
         * rankEntries.get(i).getTotalPlayTime() , countryFlag)); } else {
         * String countryFlag =
         * "/assets/images/country_flags/_United Nations.png";
         * rankEntries.set(i, new
         * RankEntryByAchievements(rankEntries.get(i).getRankNumber(),
         * rankEntries.get(i).getId64(), rankEntries.get(i).getName(),
         * rankEntries.get(i) .getAchievementsTotal(),
         * rankEntries.get(i).getCompletionRate(),
         * rankEntries.get(i).getTotalPlayTime(), countryFlag)); } }
         * 
         * }
         * 
         * model.addAttribute("rankentries", rankEntries);
         */
        return "game";
    }
}
