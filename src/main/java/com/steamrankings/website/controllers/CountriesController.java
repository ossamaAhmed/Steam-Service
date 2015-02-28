package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CountriesController {

    @RequestMapping("/countries")
    public String getCountries (String id, Model model) {
        // We add the error message to our model
    	if (id != null) {
    		return "redirect:/country?=" + id;
    	} else {
        	model.addAttribute("countries", "hello");    		
    	}

        return "countries";
    }
    
    @RequestMapping("/country")
    public String getSpecificCountry (String id, Model model) {
    	model.addAttribute("country", "yo");    		
		return "country";
    	
    }
}
