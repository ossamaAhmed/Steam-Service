package com.steamrankings.website.model;

import javax.validation.constraints.NotNull;

public class SteamProfile {
    private String id64;

    @NotNull
    public String getSteamId64() {
        return id64;
    }

    public void setSteamId64(String query) {
        this.id64 = query;
    }
}
