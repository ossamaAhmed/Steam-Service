package com.steamrankings.website.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.service.api.APIException;
import com.steamrankings.service.api.leaderboards.Leaderboards;
import com.steamrankings.service.api.leaderboards.RankEntryByAchievements;
import com.steamrankings.service.api.profiles.Profiles;
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
                        countries.add(new Country("un", Application.steam_countries.getJSONObject(key).getString("name")));
                } catch (Exception e) {
                    countries.add(new Country("un", Application.steam_countries.getJSONObject(key).getString("name")));
                }
            }

            Collections.sort(countries, new Comparator<Country>() {
                public int compare(Country o1, Country o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            Collections.reverse(countries);
            model.addAttribute("countries", countries);
            return "countries";
        } else {
        	id = id.toUpperCase();
            if (Application.steam_countries.has(id)) {
                try {
                    List<RankEntryByAchievements> rankEntries = Leaderboards.getRanksByCountry(id, 0, 0, Application.client);
                    model.addAttribute("rankentries", rankEntries);
                    if(rankEntries == null | rankEntries.isEmpty()) {
                    	model.addAttribute("error_msg", "Unfortunatly there are no players from " + Application.steam_countries.getJSONObject(id).getString("name") + " in our database.");
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