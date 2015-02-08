package com.steamrankings.website.model;

import javax.validation.constraints.NotNull;

import com.steamrankings.service.steam.SteamDataDatabase;

public class SteamProfile {
    private Long id64;


    @NotNull
    public Long getSteamId64() {
        return id64;
    }

    public void setSteamId64(String query) {
        this.id64 = SteamDataDatabase.convertToSteamId64(query);
    }

//    public void setSteamId64(String query) {
//        this.id64 = query;
//    }
//    private String detectIdOrName(String query) {
//    	if (SteamDataDatabase.convertToSteamId64(id64) == -1) {
//    		return query;
//    	} else {
//    		return id
//    	}
//    }
}
