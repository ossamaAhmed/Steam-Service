package com.steamrankings.website.model;

import javax.validation.constraints.NotNull;

import com.steamrankings.service.steam.SteamDataDatabase;

public class SteamProfile {
    private String id64;


    @NotNull
    public String getSteamId64() {
        return id64;
    }

    public void setSteamId64(String id64) {
        this.id64 = id64;
    }
}
