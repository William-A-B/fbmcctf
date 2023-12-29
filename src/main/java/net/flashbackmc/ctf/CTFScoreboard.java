package net.flashbackmc.ctf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.flashbackmc.ctf.CTFPlayer.CTFKit;

public class CTFScoreboard {

	private ScoreboardManager manager = Bukkit.getScoreboardManager();
	private final Scoreboard scoreboard = manager.getNewScoreboard();
	private final Objective objective = scoreboard.registerNewObjective("Statistics", "dummy");
	private Score score4;
	private Score score3;
	private Score score2;
	private Score score1;


	public CTFScoreboard() {
		// TODO Auto-generated constructor stub
	}

	
//	@Override
//	public boolean onCommand() {
//		
//	}
	
	public void setupScoreboard(CTFPlayer ctfP) {
		this.objective.setDisplayName(ChatColor.DARK_GREEN + "Statistics");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		this.score4 = objective.getScore(ChatColor.GOLD + "Current Kit: " + ctfP.getChosenKit().toString());
		this.score4.setScore(4);
		
		this.score3 = objective.getScore(ChatColor.GREEN + "Total Kills: " + CTFPlayer.getPlayerTotalKills(ctfP));
		this.score3.setScore(3);
		
		this.score2 = objective.getScore(ChatColor.GREEN + "Total Deaths: " + CTFPlayer.getPlayerTotalDeaths(ctfP));
		this.score2.setScore(2);
		
		this.score1 = objective.getScore(ChatColor.DARK_GREEN + "Made by Will88");
		this.score1.setScore(1);
		
		ctfP.getCtfPlayer().setScoreboard(this.scoreboard);
	}
	
	public void updateScoreboard(CTFPlayer ctfP) {
		this.score4 = objective.getScore(ChatColor.GREEN + "Current Kit: " + ChatColor.GOLD + ctfP.getChosenKit().toString());
		this.score4.setScore(4);
		
		this.score3 = objective.getScore(ChatColor.GREEN + "Total Kills: " + CTFPlayer.getPlayerTotalKills(ctfP));
		this.score3.setScore(3);
		
		this.score2 = objective.getScore(ChatColor.GREEN + "Total Deaths: " + CTFPlayer.getPlayerTotalDeaths(ctfP));
		this.score2.setScore(2);
		
		this.score1 = objective.getScore(ChatColor.DARK_GREEN + "Made by Will88");
		this.score1.setScore(1);
		
		ctfP.getCtfPlayer().setScoreboard(this.scoreboard);
	}
	
	
    /**
	 * @return the manager
	 */
	public ScoreboardManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(ScoreboardManager manager) {
		this.manager = manager;
	}
	
    /**
	 * @return the board
	 */
	public Scoreboard getBoard() {
		return scoreboard;
	}
	
	/**
	 * @return the objective
	 */
	public Objective getObjective() {
		return objective;
	}
	
	
}
