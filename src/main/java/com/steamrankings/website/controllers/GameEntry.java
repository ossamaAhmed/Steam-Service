package com.steamrankings.website.controllers;

import com.steamrankings.service.api.games.SteamGame;

public class GameEntry {
	public SteamGame steamGame;
	public String topPlayer;
	
	public GameEntry(SteamGame game, String topPlayer) {
		this.steamGame = game;
		this.topPlayer = topPlayer;
	}
	
	public SteamGame getSteamGame() {
		return this.steamGame;
	}
	
	public String getTopPlayer() {
		return this.topPlayer;
	}
}
