package net.flashbackmc.ctf.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;
import net.flashbackmc.ctf.kits.HeavyKit;

public class PlayerRespawnListener implements Listener {

	public PlayerRespawnListener() {
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
		
		CTFPlayer ctfPlayer = FBMCCTF.getCtfPlayers(player);
		
		ctfPlayer.setupPlayer();
		player.closeInventory();

	}

}
