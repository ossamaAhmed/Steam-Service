package com.steamrankings.website.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.steamrankings.service.api.profiles.Profiles;
import com.steamrankings.website.model.SteamProfile;
//import com.steamrankings.service.api.profiles.SteamProfile;


@Controller
public class IndexController extends WebMvcConfigurerAdapter {
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView index(SteamProfile profile) {
//		ModelAndView model = new ModelAndView("index");
//		return model;
//
//	}
	
     @RequestMapping(value="/", method=RequestMethod.GET)
     public String showIndex(SteamProfile profile) {
         return "index";
     }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String redirect(SteamProfile profile) {     
       return "redirect:/";
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkPersonInfo(SteamProfile steamProfile, BindingResult bindingResult) {
//    	SteamProfile profile = Profiles.getSteamUser(steamProfile);
//        if (bindingResult.hasErrors()) {
//            return "index";
//        }
        return "redirect:/profile?id=" + steamProfile.getSteamId64();
    }

}