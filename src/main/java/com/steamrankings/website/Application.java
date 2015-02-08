package com.steamrankings.website;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class Application extends WebMvcConfigurerAdapter {
    public static JSONObject steam_countries;

    public static void main(String[] args) throws IOException, JSONException {
        // InputStream is = new FileInputStream("steam_countries.json");
        byte[] encoded = Files.readAllBytes(Paths.get("steam_countries.min.json"));
        String data = new String(encoded, StandardCharsets.UTF_8);
        steam_countries = new JSONObject(data);
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }
}