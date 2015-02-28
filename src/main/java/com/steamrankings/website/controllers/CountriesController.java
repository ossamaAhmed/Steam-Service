package com.steamrankings.website.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.website.Application;

@Controller
public class CountriesController {

    @RequestMapping("/countries")
    public String getCountries(String id, Model model) {
        if (id == null || id.isEmpty() || id.equals("")) {
            ArrayList<Country> countries = new ArrayList<Country>();

            for (String key : Application.steam_countries.keySet()) {
                URL fileURL = this.getClass().getResource("/assets/images/country_flags/64/" + key + ".png");
                File f;
                try {
                    f = new File(fileURL.toURI());
                    if (f.isFile())
                        countries.add(new Country(key, Application.steam_countries.getJSONObject(key).getString("name")));
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
                model.addAttribute("country_code", id);
                model.addAttribute("country_name", Application.steam_countries.getJSONObject(id).getString("name"));
                return "country";
            } else {
                model.addAttribute("error_msg", "The country code " + id + "is invalid.");
                return "error";
            }
        }
    }
}