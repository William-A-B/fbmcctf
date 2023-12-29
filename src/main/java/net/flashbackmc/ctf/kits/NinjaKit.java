package net.flashbackmc.ctf.kits;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;
import net.flashbackmc.ctf.FBMCCTF;

public class NinjaKit extends BaseKit implements Listener {


    private ItemStack[] kitContents;

    public NinjaKit() {
		setupKitContents();
    }
    

    private void setupKitContents() {
		ItemStack sword = new ItemStack(Material.GOLD_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("Ninja Sword");
		swordMeta.spigot().setUnbreakable(true);
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, false);
		sword.setItemMeta(swordMeta);
		
		ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 10);
		ItemMeta enderpearlMeta = enderpearl.getItemMeta();
		enderpearlMeta.setDisplayName("Ninja Pearl");
		enderpearlMeta.spigot().setUnbreakable(true);
		enderpearl.setItemMeta(enderpearlMeta);

		ItemStack flashBomb = new ItemStack(Material.EGG, 10);
		ItemMeta flashBombMeta = flashBomb.getItemMeta();
		flashBombMeta.setDisplayName("Flashbomb");
		flashBombMeta.spigot().setUnbreakable(true);
		flashBomb.setItemMeta(flashBombMeta);
		
		ItemStack invisPowder = new ItemStack(Material.REDSTONE_WIRE, 64);
		ItemMeta invisPowderMeta = invisPowder.getItemMeta();
		invisPowderMeta.setDisplayName("Invis Powder");
		invisPowderMeta.spigot().setUnbreakable(true);
		invisPowder.setItemMeta(invisPowderMeta);
		
		this.kitContents = new ItemStack[]{sword, enderpearl, flashBomb, invisPowder};
	}


    public void setupPlayerInventory(Inventory playerInv) {
		
		playerInv.clear();
		
		if (FBMCCTF.getMode() ==  1) {
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(2, getKitContents()[2]);
			playerInv.setItem(3, getKitContents()[3]);
		}
		else if (FBMCCTF.getMode() == 0) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
            playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(2, getKitContents()[2]);
			playerInv.setItem(3, getKitContents()[3]);

			for (int i = 4; i < 36; i++) {
				playerInv.setItem(i, soup);
			}
		}
		
	}


	@EventHandler
	public void onNinjaFlashbombHit(ProjectileHitEvent event) {
		// The projectile
		Entity entity = event.getEntity();
		// Get the type of entity
		EntityType entityType = entity.getType();
		// Make sure its a snowball
		if (entityType == EntityType.EGG) {
			Egg flashbomb = (Egg) entity;
			ProjectileSource shooter = flashbomb.getShooter();
			// Make sure entity shooting is a player and has selected medic kit
			if (shooter instanceof Player) {
				Player flashbombThrower = (Player) shooter;
				CTFPlayer ctfFlashbombThrower = FBMCCTF.getCtfPlayers(flashbombThrower);
				if (ctfFlashbombThrower.getChosenKit() == CTFKit.NINJA) {
					Location flashbombEndLocation = flashbomb.getLocation();
					// Only replace air blocks with cobwebs
					if (flashbombEndLocation.getBlock().isEmpty()) {
						
						flashbomb.getLocation().getWorld().createExplosion(flashbombEndLocation, 0F, false);
						flashbombHit(flashbomb);
						
						/*flashbombEndLocation.getBlock().setType(Material.WEB);
						setCurrentMedicWebs(getCurrentMedicWebs()-1);
						removeWebOnGroundTimer(flashbombEndLocation);*/
					}
				}
			}
		}
	}



    private void flashbombHit(Egg flashbomb) {

		HashMap<Player, CTFPlayer> ctfPlayers = FBMCCTF.getCtfPlayers();

		for (HashMap.Entry<Player, CTFPlayer> ctfPlayer : ctfPlayers.entrySet()) {
			//Get the current ctfPlayer
			CTFPlayer ctfP = ctfPlayer.getValue();

			if (flashbomb.getLocation().distance(ctfP.getCtfPlayer().getLocation()) < 3) {
				ctfP.getCtfPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
				ctfP.getCtfPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 0));
				ctfP.getCtfPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
			}
		}
	}


	/**
	 * @return the kitContents
	 */
	public ItemStack[] getKitContents() {
		return kitContents;
	}

}


