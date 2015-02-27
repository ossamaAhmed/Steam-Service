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
            game = Games.getSteamGame(500, Application.client);
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

        model.addAttribute("name", game.getName());

        return "game";
    }
}
