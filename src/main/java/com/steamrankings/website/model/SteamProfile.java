package com.steamrankings.website.model;

import javax.validation.constraints.NotNull;

public class SteamProfile {
    private Long id64;


    @NotNull
    public Long getSteamId64() {
        return id64;
    }

    public void setSteamId64(Long id64) {
        this.id64 = id64;
    }
}
