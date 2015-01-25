package com.steamrankings.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String redirect() {
      
       return "redirect:/";
    }
}
