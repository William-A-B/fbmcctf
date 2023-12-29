package net.flashbackmc.ctf.game;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class Team {

	private String teamName;
	private Color teamColour;
	private int flagsCaptured;
	private boolean flagStolen;
	public ArrayList<Player> teamMembers = new ArrayList<Player>();
	
	
	
	public Team(String teamName, Color teamColour) {
		
		this.teamName = teamName;
		this.teamColour = teamColour;
		this.flagsCaptured = 0;
		this.flagStolen = false;
		
	}

}
