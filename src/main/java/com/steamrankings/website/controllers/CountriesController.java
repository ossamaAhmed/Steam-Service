package com.steamrankings.website.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;
import com.steamrankings.website.Application;

@Controller
public class CountriesController {

    @RequestMapping("/countries")
    public String getCountries(String id, Model model) {
        if (id == null || id.isEmpty() || id.equals("")) {
            ArrayList<Country> countries = new ArrayList<Country>();

            for (String key : Application.steam_countries.keySet()) {
            	InputStream inStream = this.getClass().getResourceAsStream("/assets/images/country_flags/64/" + key.toLowerCase() + ".png");
                try {

                    if (inStream != null)
                        countries.add(new Country(key.toLowerCase(), Application.steam_countries.getJSONObject(key).getString("name")));
                    else
                        countries.add(new Country("_United-Nations", Application.steam_countries.getJSONObject(key).getString("name")));
                } catch (Exception e) {
                    countries.add(new Country("_United-Nations", Application.steam_countries.getJSONObject(key).getString("name")));
                }
            }
            model.addAttribute("countries", countries);
            return "countries";
        } else {
            if (Application.steam_countries.has(id)) {
                try {
                    List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByCountry(id, 0, 0, Application.client);
                    model.addAttribute("rankentries", rankEntries);
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
                model.addAttribute("country_code", id.toLowerCase());
                model.addAttribute("country_name", Application.steam_countries.getJSONObject(id).getString("name"));
                return "country";
            } else {
                model.addAttribute("error_msg", "The country code " + id + "is invalid.");
                return "error";
            }
        }
    }
}