package com.steamrankings.website.controllers;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steamrankings.website.Application;
import com.steamrankings.service.api.APIException;

@Controller
public class AboutController {

    @RequestMapping("/about")
    public String getProfile(String id, Model model) throws JSONException {

    	
        try {
        	Properties properties = new Properties();
        	properties.load(Application.class.getResourceAsStream("/buildNumber.properties"));
        	
        	
			model.addAttribute("version_backend", Application.client.getBuildVersion());
			model.addAttribute("version_frontend", properties.getProperty("git-sha-1"));
			
		} catch (APIException | IOException e) {
			model.addAttribute("version_backend", "Error getting backend version");
		}

        return "about";
    }
}