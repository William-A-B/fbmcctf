package net.flashbackmc.ctf.kits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;
import net.flashbackmc.ctf.FBMCCTF;

public class SoldierKit extends BaseKit implements Listener {

	//private static final List<Material> UNCLIMABLE = Arrays.asList(Material.COAL_ORE, Material.BARRIER, Material.QUARTZ_ORE);
    private static final float EXP_PER_CLIMB = 1.0f / 7;
    private static final double MAX_CLIMB_STRENGTH = 0.9;
    //private static final Duration RESTORE_TIME = Duration.seconds(10);
    
    private static float CURRENT_EXP_PERCENT;
    private static final float EXP_REGEN = 0.01f;
	


	private ItemStack[] kitContents;
	
	public SoldierKit() {
		// Set kit content items
		setupKitContents();
	}

	
	private void setupKitContents() {
		ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("Soldier Sword");
		swordMeta.spigot().setUnbreakable(true);
		sword.setItemMeta(swordMeta);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 4);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("Soldier Helmet");
		helmetMeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName("Soldier Chestplate");
		chestplateMeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName("Soldier Leggings");
		leggingsMeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName("Soldier Boots");
		bootsMeta.spigot().setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		
		this.kitContents = new ItemStack[]{sword, steak, helmet, chestplate, leggings, boots};
	}
	
	
	public void setupPlayerInventory(Inventory playerInv) {
		
		playerInv.clear();

		if (FBMCCTF.getMode() ==  1) {
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(39, getKitContents()[2]);
			playerInv.setItem(38, getKitContents()[3]);
			playerInv.setItem(37, getKitContents()[4]);
			playerInv.setItem(36, getKitContents()[5]);
		}
		else if (FBMCCTF.getMode() == 0) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(39, getKitContents()[2]);
			playerInv.setItem(38, getKitContents()[3]);
			playerInv.setItem(37, getKitContents()[4]);
			playerInv.setItem(36, getKitContents()[5]);

			for (int i = 1; i < 36; i++) {
				playerInv.setItem(i, soup);
			}
		}
		
	}
	
	
	@EventHandler
	private void onClick(PlayerInteractEvent event) {
//        if (!Util.isRightClick(e) || e.getClickedBlock() == null || !allowed())
//            return;
//        if (UNCLIMABLE.contains(e.getClickedBlock().getType())) {
//            //getOwner().sendTip("CTF_SOLDIER_WALLCLIMB");
//            return;
//        }
		
		//Define the player
		Player player = event.getPlayer();
		//Define the item in their hand
		Material mat = player.getItemInHand().getType();
		
		//If the player right clicks air or a block
		//event.getAction() == Action.RIGHT_CLICK_AIR || 
		// 
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && mat == Material.IRON_SWORD) {
			
			CTFPlayer currentCTFPlayer = FBMCCTF.getCtfPlayers(player);
	        
			if (currentCTFPlayer.getChosenKit() != CTFKit.SOLDIER || currentCTFPlayer.getChosenKit() == null) {
				event.setCancelled(true);
				return;
			}
			
			boolean delay = true;
	        
			
			if (getCurrentExpPercent() > EXP_PER_CLIMB) {
				
				// Push the player away from any wall they are near
		        
		        if (player.getLocation().add(0, 0, 0.35).getBlock().getType() != Material.AIR)
		            player.setVelocity(new Vector(0, 0, -1));
		        else if (player.getLocation().add(0, 0, -0.35).getBlock().getType() != Material.AIR)
		            player.setVelocity(new Vector(0, 0, 1));
		        else if (player.getLocation().add(0.35, 0, 0).getBlock().getType() != Material.AIR)
		            player.setVelocity(new Vector(-1, 0, 0));
		        else if (player.getLocation().add(-0.35, 0, 0).getBlock().getType() != Material.AIR)
		            player.setVelocity(new Vector(1, 0, 0));
		        else
		            delay = false;
		
		        if (delay)
		            player.setVelocity(new Vector(0, getClimbStrength(), 0));
		        else
		            player.setVelocity(new Vector(0, getClimbStrength(), 0));
				
				
	        	player.setExp(player.getExp() - EXP_PER_CLIMB);
	        	setCurrentExpPercent(player.getExp() - EXP_PER_CLIMB);
	        }
		}
	}
	
	
	public void passiveExpRegeneration() {
        
        
        new BukkitRunnable() {
			
			@Override
			public void run() {
				HashMap<Player, CTFPlayer> ctfPlayers = FBMCCTF.getCtfPlayers();
				ItemStack snowball;	
				ItemStack newSnowball = new ItemStack(Material.SNOW_BALL, 1);
				int numSoldiers = 0;
				
				for (HashMap.Entry<Player, CTFPlayer> ctfPlayer : ctfPlayers.entrySet()) {
					//Player p = ctfPlayer.getKey();
					CTFPlayer ctfP = ctfPlayer.getValue();
					float currentExp = ctfP.getCtfPlayer().getExp();
					int currentLevel = ctfP.getCtfPlayer().getLevel();
					
					// Count up number of soldiers in the hashmap
					// Only execute if player has selected medic
					if (ctfP.getChosenKit() == CTFKit.SOLDIER) {
						numSoldiers++;
						
						if (currentLevel >= 1) {
							ctfP.getCtfPlayer().setLevel(0);
						}
						
						if (currentExp < 1.0f) {
							ctfP.getCtfPlayer().setExp(getCurrentExpPercent() + EXP_REGEN);
							setCurrentExpPercent(getCurrentExpPercent() + EXP_REGEN);
						}
						
					}
					else if (ctfP.getChosenKit() != CTFKit.SOLDIER) {
						ctfP.getCtfPlayer().setLevel(0);
						ctfP.getCtfPlayer().setExp(0.0f);
					}
				}
				// If no soldiers playing, cancel runnable
				if (numSoldiers == 0) {
					cancel();
				}
			}
		}.runTaskTimer(FBMCCTF.getPlugin(), 0L, 1L);
	}
	
	
	@EventHandler
	private void onFallDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		
		if (entity instanceof Player) {
			Player p = (Player) entity;
			CTFPlayer currentCTFPlayer = FBMCCTF.getCtfPlayers(p);
			
			if (currentCTFPlayer.getChosenKit() == CTFKit.SOLDIER) {
				if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
					event.setCancelled(true);
				}
			}
			else {
				return;
			}
		}
		
		
	}
	
    private double getClimbStrength() {
        return MAX_CLIMB_STRENGTH;
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
	
	/**
	 * @return the cURRENT_EXP_PERCENT
	 */
	public static float getCurrentExpPercent() {
		return CURRENT_EXP_PERCENT;
	}


	/**
	 * @param cURRENT_EXP_PERCENT the cURRENT_EXP_PERCENT to set
	 */
	public static void setCurrentExpPercent(float currentExpPercent) {
		CURRENT_EXP_PERCENT = currentExpPercent;
	}

	
	
}
