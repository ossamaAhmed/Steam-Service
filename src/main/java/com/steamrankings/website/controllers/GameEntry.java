package com.steamrankings.website.controllers;

import com.steamrankings.service.api.games.SteamGame;

public class GameEntry {
	public SteamGame game;
	public String topPlayer;
	
	public GameEntry(SteamGame game, String topPlayer) {
		this.game = game;
		this.topPlayer = topPlayer;
	}
	
	public SteamGame getSteamGame() {
		return this.game;
	}
	
	public String getTopPlayer() {
		return this.topPlayer;
	}
}
