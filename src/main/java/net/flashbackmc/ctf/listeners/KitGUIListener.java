package net.flashbackmc.ctf.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;
import net.flashbackmc.ctf.kits.ArcherKit;
import net.flashbackmc.ctf.kits.HeavyKit;
import net.flashbackmc.ctf.kits.MedicKit;
import net.flashbackmc.ctf.kits.NinjaKit;
import net.flashbackmc.ctf.kits.SoldierKit;
import net.md_5.bungee.api.ChatColor;

public class KitGUIListener implements Listener {

	public KitGUIListener() {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onMenuClick(InventoryClickEvent event) {
		
		if (event.getClickedInventory().getTitle().equalsIgnoreCase("Class Selector")) {
			
			if (event.getCurrentItem() == null) {
				return;
			}
			
			Player p = (Player) event.getWhoClicked();
			Inventory inv = p.getInventory();
			CTFPlayer ctfPlayer = new CTFPlayer(p, CTFKit.HEAVY, new HeavyKit(), inv);
			
			
			// Heavy Class selected
			if (event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				p.sendMessage(ChatColor.GREEN + "You selected the " + ChatColor.GOLD + "HEAVY" + ChatColor.GREEN + " class!");
				
				HeavyKit heavy = new HeavyKit();
				
				ctfPlayer.setChosenKit(CTFKit.HEAVY, heavy);
				FBMCCTF.addCtfPlayer(p, ctfPlayer);
				
				ctfPlayer.setupPlayer();
				p.closeInventory();
			}
			// Soldier Class selected
			else if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
				p.sendMessage(ChatColor.GREEN + "You selected the " + ChatColor.GOLD + "SOLDIER" + ChatColor.GREEN + " class!");
				
				SoldierKit soldier = new SoldierKit();
				
				ctfPlayer.setChosenKit(CTFKit.SOLDIER, soldier);
				FBMCCTF.addCtfPlayer(p, ctfPlayer);
				
				ctfPlayer.setupPlayer();
				p.closeInventory();
				SoldierKit.setCurrentExpPercent(1.0f);
				soldier.passiveExpRegeneration();
			}
			// Medic Class selected
			else if (event.getCurrentItem().getType() == Material.GOLD_SWORD) {
				p.sendMessage(ChatColor.GREEN + "You selected the " + ChatColor.GOLD + "MEDIC" + ChatColor.GREEN + " class!");
				
				MedicKit medic = new MedicKit();

				ctfPlayer.setChosenKit(CTFKit.MEDIC, medic);
				FBMCCTF.addCtfPlayer(p, ctfPlayer);
				
				ctfPlayer.setupPlayer();
				p.closeInventory();
				medic.replenishMedicWebs();
				medic.passiveRegeneration();
			}
			// Archer Class selected
			else if (event.getCurrentItem().getType() == Material.BOW) {
				p.sendMessage(ChatColor.GREEN + "You selected the " + ChatColor.GOLD + "ARCHER" + ChatColor.GREEN + " class!");
				
				ArcherKit archer = new ArcherKit();

				ctfPlayer.setChosenKit(CTFKit.ARCHER, archer);
				FBMCCTF.addCtfPlayer(p, ctfPlayer);
				
				ctfPlayer.setupPlayer();
				p.closeInventory();
				
			}
			// Ninja Class selected
			else if (event.getCurrentItem().getType() == Material.ENDER_PEARL) {
				p.sendMessage(ChatColor.GREEN + "You selected the " + ChatColor.GOLD + "NINJA" + ChatColor.GREEN + " class!");
				
				NinjaKit ninja = new NinjaKit();

				ctfPlayer.setChosenKit(CTFKit.NINJA, ninja);
				FBMCCTF.addCtfPlayer(p, ctfPlayer);
				
				ctfPlayer.setupPlayer();
				p.closeInventory();
			}

			// Players cannot move items
			event.setCancelled(true);
		}
	}
	
}
