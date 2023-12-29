package net.flashbackmc.ctf.kits;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;

public class MedicKit extends BaseKit implements Listener {
	
	private ItemStack[] kitContents;
	private static int TOTAL_MEDIC_WEBS = 3;
	private static int CURRENT_MEDIC_WEBS;
	
	public MedicKit() {
		// Set kit content items
		setupKitContents();
	}

	
	private void setupKitContents() {
		ItemStack sword = new ItemStack(Material.GOLD_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("Medic Sword");
		swordMeta.spigot().setUnbreakable(true);
		sword.setItemMeta(swordMeta);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 6);
		
		ItemStack cobweb = new ItemStack(Material.SNOW_BALL, TOTAL_MEDIC_WEBS);
		ItemMeta cobwebMeta = cobweb.getItemMeta();
		cobwebMeta.setDisplayName("Medic Web");
		cobweb.setItemMeta(cobwebMeta);
		resetMedicWebs();
		
		ItemStack helmet = new ItemStack(Material.GOLD_HELMET, 1);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("Medic Helmet");
		helmetMeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.GOLD_CHESTPLATE, 1);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName("Medic Chestplate");
		chestplateMeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.GOLD_LEGGINGS, 1);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName("Medic Leggings");
		leggingsMeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack boots = new ItemStack(Material.GOLD_BOOTS, 1);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName("Medic Boots");
		bootsMeta.spigot().setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		
		this.kitContents = new ItemStack[]{sword, steak, cobweb, helmet, chestplate, leggings, boots};
	}
	
	
	public void setupPlayerInventory(Inventory playerInv) {
		
		playerInv.clear();
		


		if (FBMCCTF.getMode() ==  1) {
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(2, getKitContents()[2]);
			playerInv.setItem(39, getKitContents()[3]);
			playerInv.setItem(38, getKitContents()[4]);
			playerInv.setItem(37, getKitContents()[5]);
			playerInv.setItem(36, getKitContents()[6]);
		}
		else if (FBMCCTF.getMode() == 0) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[2]);
			playerInv.setItem(39, getKitContents()[3]);
			playerInv.setItem(38, getKitContents()[4]);
			playerInv.setItem(37, getKitContents()[5]);
			playerInv.setItem(36, getKitContents()[6]);

			for (int i = 2; i < 36; i++) {
				playerInv.setItem(i, soup);
			}
		}
		
		resetMedicWebs();
		
	}
	
	
	@EventHandler
	private void onClick(PlayerInteractEvent event) {
		
		//Define the player
		Player player = event.getPlayer();
		//Define the item in their hand
		Material mat = player.getItemInHand().getType();
		
		if(event.getAction() == Action.LEFT_CLICK_AIR && mat == Material.GOLD_SWORD) {
			//player.sendMessage("Healing");
		}
		
	}
	
	@EventHandler
	public void onMedicWebHit(ProjectileHitEvent event) {
		// The projectile
		Entity entity = event.getEntity();
		// Get the type of entity
		EntityType entityType = entity.getType();
		// Make sure its a snowball
		if (entityType == EntityType.SNOWBALL) {
			Snowball snowball = (Snowball) entity;
			ProjectileSource shooter = snowball.getShooter();
			// Make sure entity shooting is a player and has selected medic kit
			if (shooter instanceof Player) {
				Player snowballThrower = (Player) shooter;
				CTFPlayer ctfSnowballThrower = FBMCCTF.getCtfPlayers(snowballThrower);
				if (ctfSnowballThrower.getChosenKit() == CTFKit.MEDIC) {
					Location snowballEndLocation = snowball.getLocation();
					// Only replace air blocks with cobwebs
					if (snowballEndLocation.getBlock().isEmpty()) {
						snowballEndLocation.getBlock().setType(Material.WEB);
						setCurrentMedicWebs(getCurrentMedicWebs()-1);
						removeWebOnGroundTimer(snowballEndLocation);
					}
				}
			}
		}
	}
	
	
	private void removeWebOnGroundTimer(Location blockLocation) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
						
				if (blockLocation.getBlock().getType() == Material.WEB) {
					blockLocation.getBlock().setType(Material.AIR);
				}
				else {
					cancel();
				}
				
			}
		}.runTaskLater(FBMCCTF.getPlugin(), 60L);
	}
	
	public void replenishMedicWebs() {
		

		new BukkitRunnable() {
			
			@Override
			public void run() {
				HashMap<Player, CTFPlayer> ctfPlayers = FBMCCTF.getCtfPlayers();
				ItemStack snowball;	
				ItemStack newSnowball = new ItemStack(Material.SNOW_BALL, 1);
				int numMedics = 0;
				
				for (HashMap.Entry<Player, CTFPlayer> ctfPlayer : ctfPlayers.entrySet()) {
					//Player p = ctfPlayer.getKey();
					CTFPlayer ctfP = ctfPlayer.getValue();
					
					// Count up number of medics in the hashmap
					// Only execute if player has selected medic
					if (ctfP.getChosenKit() == CTFKit.MEDIC) {
						numMedics++;
						
						
						// Get the inventory slot of the first snowball
						int invSlot = ctfP.getInventory().first(Material.SNOW_BALL);
						
						// If no snowball, then add a new snowball into the first empty slot
						// Else just replenish the snowball stack by 1
						if (invSlot == -1) {
							int emptySlot = ctfP.getInventory().firstEmpty();
							ctfP.getInventory().setItem(emptySlot, newSnowball);
							setCurrentMedicWebs(1);
						}
						else {
							snowball = ctfP.getInventory().getItem(invSlot);
						
							if (snowball.getAmount() < TOTAL_MEDIC_WEBS) {
								setCurrentMedicWebs(getCurrentMedicWebs()+1);
								snowball.setAmount(getCurrentMedicWebs());
								ctfP.getInventory().setItem(invSlot, snowball);
							}
						}
					}
				}
				// If no medics playing, cancel runnable
				if (numMedics == 0) {
					cancel();
				}
			}
		}.runTaskTimer(FBMCCTF.getPlugin(), 0L, 100L);
	}
	
	
	public void passiveRegeneration() {
            
            
        new BukkitRunnable() {
			
			@Override
			public void run() {
				HashMap<Player, CTFPlayer> ctfPlayers = FBMCCTF.getCtfPlayers();
				ItemStack snowball;	
				ItemStack newSnowball = new ItemStack(Material.SNOW_BALL, 1);
				int numMedics = 0;
				
				for (HashMap.Entry<Player, CTFPlayer> ctfPlayer : ctfPlayers.entrySet()) {
					//Player p = ctfPlayer.getKey();
					CTFPlayer ctfP = ctfPlayer.getValue();
					double currentHealth = ctfP.getCtfPlayer().getHealth();
					
					// Count up number of medics in the hashmap
					// Only execute if player has selected medic
					if (ctfP.getChosenKit() == CTFKit.MEDIC) {
						numMedics++;
						
						if (currentHealth < 20.0) {
							if ((currentHealth + 1.0) >= 20.0) {
								ctfP.getCtfPlayer().setHealth(20.0);
							}
							else {
								ctfP.getCtfPlayer().setHealth(currentHealth + 1.0);
							}
							
						}
						
					}
				}
				// If no medics playing, cancel runnable
				if (numMedics == 0) {
					cancel();
				}
			}
		}.runTaskTimer(FBMCCTF.getPlugin(), 0L, 30L);
	}
	
	
	/**
	 * @return the kitContents
	 */
	public ItemStack[] getKitContents() {
		return kitContents;
	}


	/**
	 * @param kitContents the kitContents to set
	 */
	public void setKitContents(ItemStack[] kitContents) {
		this.kitContents = kitContents;
	}
	
	private int getCurrentMedicWebs() {
		return CURRENT_MEDIC_WEBS;
	}
	
	private void resetMedicWebs() {
		CURRENT_MEDIC_WEBS = 3;
	}
	
	private void setCurrentMedicWebs(int webs) {
		CURRENT_MEDIC_WEBS = webs;
	}

}
