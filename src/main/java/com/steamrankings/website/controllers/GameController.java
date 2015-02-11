package com.steamrankings.website.controllers;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.games.Games;
import com.steamrankings.service.api.games.SteamGame;

@Controller
public class GameController {
    @RequestMapping("/game")
    public String getGame(String id, Model model) throws JSONException {
        // We add the error message to our model
    	
    	SteamGame game = null;
//    	try {
    		game = Games.getSteamGame(500);
//    	}
//        catch (Exception e) {
//        	if (e instanceof APIException) {
//        		return "redirect:/?error=" + e.getMessage();
//        	}
//        }
    	
//        if (game == null) {
//            return "index";
//        }

//        model.addAttribute("logoUrl", game.getLogoUrl());
        model.addAttribute("name", game.getName());        
//        model.ad
        return "game";
    }
}
