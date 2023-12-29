package net.flashbackmc.ctf.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;
import net.flashbackmc.ctf.kits.HeavyKit;

public class PlayerJoinQuitListener implements Listener {

	public PlayerJoinQuitListener() {
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
		
		HeavyKit heavy = new HeavyKit();
		
		CTFPlayer ctfPlayer = new CTFPlayer(player, CTFKit.HEAVY, heavy, inv);
		FBMCCTF.addCtfPlayer(player, ctfPlayer);
		
		ctfPlayer.setupPlayer();
		
		player.closeInventory();
		
		

	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
		inv.clear();
		FBMCCTF.removeCtfPlayer(player);
		
	}

}
