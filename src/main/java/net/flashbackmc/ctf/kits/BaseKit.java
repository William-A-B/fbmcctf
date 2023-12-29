package net.flashbackmc.ctf.kits;



import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.flashbackmc.ctf.FBMCCTF;


public class BaseKit implements Listener {
	
	
	public BaseKit() {
		// TODO Auto-generated constructor stub
		
	}
	
	@EventHandler
	private void onInstantHeal(PlayerInteractEvent event) {
		//Define the player
		Player player = event.getPlayer();
		//Get Players health
		double pHealth = player.getHealth();
		//Define the item in their hand and get associated attributes
		ItemStack handItem = player.getItemInHand();
		int handItemAmount = handItem.getAmount();
		Material handMat = handItem.getType();
		//Define a bowl item
		ItemStack bowlItem = new ItemStack(Material.BOWL);

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(handMat == Material.COOKED_BEEF && FBMCCTF.getMode() == 1) {
				
				if (pHealth == 20.0) {
					event.setCancelled(true);
					return;
				}
				
				pHealth = pHealth + 8.0;
				
				if (pHealth > 20.0) {
					pHealth = 20.0;
				}
				player.setHealth(pHealth);
				
				handItem.setAmount(handItemAmount-1);
				player.setItemInHand(handItem);
				
			}
			else if (handMat == Material.MUSHROOM_SOUP && FBMCCTF.getMode() == 0) {
				
				
				if (pHealth == 20.0) {
					event.setCancelled(true);
					return;
				}
				
				pHealth = pHealth + 7.0;
				
				if (pHealth > 20.0) {
					pHealth = 20.0;
				}
				player.setHealth(pHealth);
				
				player.setItemInHand(bowlItem);
				
			}
		}
		
		
	}
	
	
	public void setupPlayerInventory(Inventory playerInv) {
		
	}
	

}
