package net.flashbackmc.ctf;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.Inventory;

import net.flashbackmc.ctf.kits.BaseKit;
import net.flashbackmc.ctf.kits.HeavyKit;

/**
 * Represents a player playing CTF
 * 
 * @author William
 *
 */
public class CTFPlayer implements Listener {

	public static enum CTFKit {
	    HEAVY,
	    SOLDIER,
	    MEDIC,
	    ARCHER,
	    ASSASSIN,
	    CHEMIST,
	    DWARF,
	    ELF,
	    ENGINEER,
	    MAGE,
	    NECRO,
	    NINJA,
	    PYRO,
	    SCOUT,
	    FASHIONISTA
	}
	
	private Player ctfPlayer;
	private CTFKit chosenKit;
	private BaseKit kit;
	private Inventory inventory;
	
	private static HashMap<CTFPlayer, Integer> totalKills = new HashMap<CTFPlayer, Integer>();
	private static HashMap<CTFPlayer, Integer> totalDeaths = new HashMap<CTFPlayer, Integer>();
	

	//Register Player
	public CTFPlayer(Player player, CTFKit chosenKit, BaseKit kit, Inventory inv) {
		
		this.ctfPlayer = player;
		this.chosenKit = chosenKit;
		this.kit = kit;
		this.inventory = inv;
		
	}
	//Register Listeners
	public CTFPlayer() {
		
	}
	

	

	public void setupPlayer() {
		
		
		kit.setupPlayerInventory(inventory);
		
//		playerInv.clear();
//		playerInv.setItem(0, getKitContents()[0]);
//		playerInv.setItem(1, getKitContents()[1]);
//		playerInv.setItem(39, getKitContents()[2]);
//		playerInv.setItem(38, getKitContents()[3]);
//		playerInv.setItem(37, getKitContents()[4]);
//		playerInv.setItem(36, getKitContents()[5]);
		
	}

	
	/***
	 * Cancel all regeneration from full hunger or natural regeneration
	 * 
	 * @param event - the regeneration event
	 */
	@EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        if(event.getRegainReason() == RegainReason.SATIATED || event.getRegainReason() == RegainReason.REGEN) {
        	event.setCancelled(true);
        }
    }
	

	/***
	 * Cancel all hunger depletion and set food level to max
	 * 
	 * @param event - The event occurring at a food level change
	 */
	@EventHandler
	public void onHungerDepletion(FoodLevelChangeEvent event) {
		event.setFoodLevel(20);
		event.setCancelled(true);
	}

	private void addToHash(Player p, CTFKit chosenKit) {
		FBMCCTF.addCtfPlayer(p, this);
	}
	
	/**
	 * @return the chosenKit
	 */
	public CTFKit getChosenKit() {
		return chosenKit;
	}

	/**
	 * @param chosenKit the chosenKit to set
	 */
	public void setChosenKit(CTFKit chosenKit, BaseKit kit) {
		this.chosenKit = chosenKit;
		this.kit = kit;
	}

	
	public void setupCtfKit(Player player, CTFKit kit) {
		
		FBMCCTF.getCtfPlayers(player).getChosenKit();
	}

	
	/**
	 * @return the ctfPlayer
	 */
	public Player getCtfPlayer() {
		return ctfPlayer;
	}
	
	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * @return the totalKills
	 */
	public static HashMap<CTFPlayer, Integer> getTotalKills() {
		return totalKills;
	}
	
	/**
	 * @return the totalKills for a player
	 */
	public static int getPlayerTotalKills(CTFPlayer ctfP) {
		return totalKills.get(ctfP);
	}
	
	/**
	 * @return the totalDeaths for a player
	 */
	public static int getPlayerTotalDeaths(CTFPlayer ctfP) {
		return totalDeaths.get(ctfP);
	}
	
	/**
	 * Increments a players total number of kills by one.
	 */
	public static void incrementPlayerTotalKills(CTFPlayer ctfP) {
		totalKills.put(ctfP, getPlayerTotalKills(ctfP) + 1);
	}
	
	/**
	 * Increments a players total number of deaths by one.
	 */
	public static void incrementPlayerTotalDeaths(CTFPlayer ctfP) {
		totalDeaths.put(ctfP, getPlayerTotalDeaths(ctfP) + 1);
	}
	
}
