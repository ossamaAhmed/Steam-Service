package com.steamrankings.website.model;

import javax.validation.constraints.NotNull;

public class SteamProfile {
    private String id64OrName;

    @NotNull
    public String getUserQuery() {
        return id64OrName;
    }

    public void setUserQuery(String query) {
        this.id64OrName = query;
    }
}
