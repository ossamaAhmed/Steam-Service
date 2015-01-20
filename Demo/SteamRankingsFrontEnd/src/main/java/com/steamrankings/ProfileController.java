package com.steamrankings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// HTTP requests handled by the controller
// You identify controllers in Spring by using the annotation @Controller
@Controller
public class ProfileController {
	
	// Good Spring MVC tutorial at https://spring.io/guides/gs/serving-web-content/
	
	// This annotation sets that the HTTP requests of /profile are handled by the getProfile method
	@RequestMapping("/profile")
	public String getProfile(String id, Model model) {
		if (id == null) {
			// We add the error message to our model
			model.addAttribute("message", "Invalid Steam user ID");
			model.addAttribute("url", "http://steamcommunity.com");
			return "profile";
		}

		Map<String, String> map = null;
		try {
			map = getSteamNameFromId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (map == null) {
			// We add the error message to our model
			model.addAttribute("message", "Invalid Steam user ID");
			model.addAttribute("url", "http://steamcommunity.com");
		} else {
			// We add our hello message to the model
			model.addAttribute("message", "Hello, " + map.get("personaname").trim() + "!");
			model.addAttribute("url", map.get("profileurl"));
		}
		
		// In the return you specify the name of the view (profile.html)
		return "profile";
	}

	public Map<String, String> getSteamNameFromId(String id) throws Exception {
		// Replace XXXXX with the Steam API key found on OneDrive in ECSE 428 Project\API examples\apikey.txt
		// PLEASE DO NOT PUSH TO GITHUB WITH API KEY IN CODE
		SteamrollerApi api = new SteamrollerApi("XXXXXX");
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println(id);
		
		// Specify our steam ID parameter
		param.put("steamids", id);
		
		// Calling the Steam API through steam-condenser library
		// We specify the following:
		// The interface ISteamUser
		// The API method GetPlaterSummaries
		// The version number 2
		// And our paramaters
		// All this info can be found at https://developer.valvesoftware.com/wiki/Steam_Web_API
		String jsonString = api.getJSON("ISteamUser", "GetPlayerSummaries", 2, param);
		System.out.println(jsonString);
		
		// The JSON object returned by Steam
		JSONObject json = new JSONObject(jsonString);
		System.out.println(json);
		
		// Now we just search for the variable "personaname" that contains the value we are looking for
		
		json = (JSONObject) json.get("response");
		System.out.println(json);

		JSONArray jsonArray = json.getJSONArray("players");
		System.out.println(jsonArray);

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonElement = jsonArray.getJSONObject(i);
			Iterator itr = jsonElement.keys();
			while (itr.hasNext()) {
				String element = (String) itr.next();
				if (element.equals("personaname")) {
					map.put("personaname", jsonElement.getString("personaname"));
				} else if (element.equals("profileurl")) {
					map.put("profileurl", jsonElement.getString("profileurl"));
				}
			}
		}

		return map;
	}
}