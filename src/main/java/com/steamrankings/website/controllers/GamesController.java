package com.steamrankings.website.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;
import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;
import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.website.Application;

@Controller
public class GamesController {

    @RequestMapping("/games")
    public String getGames(String id, Model model) {
        if(id == null || id.isEmpty()) {
            List<SteamGame> steamGames = null;
            ArrayList<GameEntry> gameEntries = new ArrayList<GameEntry>();
            try {
                steamGames = Games.getSteamGames(Application.client);
                for(SteamGame steamGame : steamGames) {
                	gameEntries.add(new GameEntry(steamGame, Profiles.getTopGamePlayer(Integer.toString(steamGame.getAppId()), Application.client)));
                }
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
            model.addAttribute("games", gameEntries);
            model.addAttribute("game_name", gameEntries.get(Integer.parseInt(id)).getSteamGame().getName());
            return "gameslist";
        } else {
            List<RankEntryByAchievements> rankEntries = null;
            try {
				SteamGame steamGame = Games.getSteamGame(Integer.parseInt(id), Application.client);
			} catch (JsonParseException e1) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
			} catch (JsonMappingException e1) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
			} catch (NumberFormatException e1) {
				model.addAttribute("error_msg", id + " is an invalid game ID.");
				return "error";
			} catch (APIException e1) {
				model.addAttribute("error_msg", e1.getMessage());
				return "error";
			} catch (IOException e1) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
			}
            
            try {
                rankEntries = Leaderboards.getRanksByGameLeaderboard(Integer.parseInt(id), 0, 0, Application.client);
                
                if (rankEntries != null) {
                    for (int i = 0; i < rankEntries.size(); i++) {
                        if (rankEntries.get(i).getCountryCode() != null && !rankEntries.get(i).getCountryCode().equals("")) {
                            String countryFlag = rankEntries.get(i).getCountryCode().toLowerCase();
                            rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                                    .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                        } else {
                            String countryFlag = "un";
                            rankEntries.set(i, new RankEntryByAchievements(rankEntries.get(i).getRankNumber(), rankEntries.get(i).getId64(), rankEntries.get(i).getName(), rankEntries.get(i)
                                    .getAchievementsTotal(), rankEntries.get(i).getCompletionRate(), rankEntries.get(i).getTotalPlayTime(), countryFlag));
                        }
                    }
                    model.addAttribute("rankentries", rankEntries);
                    return "game";
                }  
                
            } catch (NumberFormatException e) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
            } catch (ClientProtocolException e) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
            } catch (APIException e) {
				model.addAttribute("error_msg", e.getMessage());
				return "error";
            } catch (IOException e) {
				model.addAttribute("error_msg", "Sorry but something went wrong.");
				return "error";
            }
            

            return "game";
        }
    }
}
