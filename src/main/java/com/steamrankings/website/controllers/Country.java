package com.steamrankings.website.controllers;

public class Country {
    public String code;
    public String name;
    
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getUpperCaseCode() {
    	return code.toUpperCase();
    }
    
    public String getName() {
    	return name;
    }
}