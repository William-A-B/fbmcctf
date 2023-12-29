package net.flashbackmc.ctf.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import net.flashbackmc.ctf.CTFPlayer;

public class Arena {

	private Location upperBound;
	private Location lowerBound;
	
	private Location joinLocation;
	private Location leaveLocation;
	
	private Location redSpawn;
	private Location blueSpawn;
	
	private Location redFlag;
	private Location blueFlag;
	
	private int arenaID;
	
	private List<CTFPlayer> ctfPlayers = new ArrayList<CTFPlayer>();
	
	private boolean inGame;
	
	public Arena(Location upperBound, Location lowerBound, Location joinLocation, Location leaveLocation,
			Location redSpawn, Location blueSpawn, Location redFlag, Location blueFlag, int arenaID) {
		
		this.setUpperBound(upperBound);
		this.setLowerBound(lowerBound);
		this.joinLocation = joinLocation;
		this.leaveLocation = leaveLocation;
		this.redSpawn = redSpawn;
		this.blueSpawn = blueSpawn;
		this.redFlag = redFlag;
		this.blueFlag = blueFlag;
		this.arenaID = arenaID;
	}
	
	public Arena(Location joinLocation, Location leaveLocation, Location redSpawn, Location blueSpawn,
			Location redFlag, Location blueFlag, int arenaID) {
		
		this.joinLocation = joinLocation;
		this.leaveLocation = leaveLocation;
		this.redSpawn = redSpawn;
		this.blueSpawn = blueSpawn;
		this.redFlag = redFlag;
		this.blueFlag = blueFlag;
		this.arenaID = arenaID;
	}

	
	/**
	 * @return the upperBound
	 */
	public Location getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound the upperBound to set
	 */
	public void setUpperBound(Location upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @return the lowerBound
	 */
	public Location getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound the lowerBound to set
	 */
	public void setLowerBound(Location lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the joinLocation
	 */
	public Location getJoinLocation() {
		return joinLocation;
	}

	/**
	 * @param joinLocation the joinLocation to set
	 */
	public void setJoinLocation(Location joinLocation) {
		this.joinLocation = joinLocation;
	}

	/**
	 * @return the leaveLocation
	 */
	public Location getLeaveLocation() {
		return leaveLocation;
	}

	/**
	 * @param leaveLocation the leaveLocation to set
	 */
	public void setLeaveLocation(Location leaveLocation) {
		this.leaveLocation = leaveLocation;
	}

	/**
	 * @return the redSpawn
	 */
	public Location getRedSpawn() {
		return redSpawn;
	}

	/**
	 * @param redSpawn the redSpawn to set
	 */
	public void setRedSpawn(Location redSpawn) {
		this.redSpawn = redSpawn;
	}

	/**
	 * @return the blueSpawn
	 */
	public Location getBlueSpawn() {
		return blueSpawn;
	}

	/**
	 * @param blueSpawn the blueSpawn to set
	 */
	public void setBlueSpawn(Location blueSpawn) {
		this.blueSpawn = blueSpawn;
	}

	/**
	 * @return the redFlag
	 */
	public Location getRedFlag() {
		return redFlag;
	}


	/**
	 * @param redFlag the redFlag to set
	 */
	public void setRedFlag(Location redFlag) {
		this.redFlag = redFlag;
	}


	/**
	 * @return the blueFlag
	 */
	public Location getBlueFlag() {
		return blueFlag;
	}


	/**
	 * @param blueFlag the blueFlag to set
	 */
	public void setBlueFlag(Location blueFlag) {
		this.blueFlag = blueFlag;
	}


	/**
	 * @return the arenaID
	 */
	public int getArenaID() {
		return arenaID;
	}

	/**
	 * @param arenaID the arenaID to set
	 */
	public void setArenaID(int arenaID) {
		this.arenaID = arenaID;
	}

	/**
	 * @return the players
	 */
	public List<CTFPlayer> getPlayers() {
		return ctfPlayers;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<CTFPlayer> players) {
		this.ctfPlayers = players;
	}

	/**
	 * @return the inGame
	 */
	public Boolean getInGame() {
		return inGame;
	}

	/**
	 * @param inGame the inGame to set
	 */
	public void setInGame(Boolean inGame) {
		this.inGame = inGame;
	}

}
