package net.flashbackmc.ctf.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BreakPlaceBlockListener implements Listener {

	public BreakPlaceBlockListener() {
		// TODO Auto-generated constructor stub
	}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.getPlayer().hasPermission("fbmcctf.build")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.getPlayer().hasPermission("fbmcctf.build")) {
			event.setCancelled(true);
		}
	}

}
