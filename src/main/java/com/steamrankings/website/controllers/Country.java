package com.steamrankings.website.controllers;

public class Country {
    public String code;
    public String name;
    public String topPlayer;
    
    public Country(String code, String name, String topPlayer) {
        this.code = code;
        this.topPlayer = topPlayer;
        this.name = name;
    }
    
    public String getUpperCaseCode() {
    	return code.toUpperCase();
    }
    
    public String getName() {
    	return name;
    }
    
    public String getTopPlayer() {
    	return topPlayer;
    }
}