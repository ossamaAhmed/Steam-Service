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
        // We add the error message to our model
        ArrayList<Country> countries = new ArrayList<Country>();

        for (String key : Application.steam_countries.keySet()) {
            URL fileURL = this.getClass().getResource("/assets/images/country_flags/" + key + ".png");
            File f;
            try {
                f = new File(fileURL.toURI());
                if (f.isFile())
                    countries.add(new Country(key, Application.steam_countries.getJSONObject(key).getString("name")));
                else
                    countries.add(new Country("_United Nations", Application.steam_countries.getJSONObject(key).getString("name")));
            } catch (Exception e) {
                countries.add(new Country("_United Nations", Application.steam_countries.getJSONObject(key).getString("name")));
            }
        }

        if (id != null) {
            return "redirect:/country?=" + id;
        } else {
            model.addAttribute("countries", countries);
        }

        return "countries";
    }

    @RequestMapping("/country")
    public String getSpecificCountry(String id, Model model) {
        model.addAttribute("country", "yo");
        return "country";
    }
}