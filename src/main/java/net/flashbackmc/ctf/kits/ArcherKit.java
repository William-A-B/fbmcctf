package net.flashbackmc.ctf.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;
import net.flashbackmc.ctf.CTFPlayer.CTFKit;




public class ArcherKit extends BaseKit implements Listener {

	private ItemStack[] kitContents;
	private static final int HEADSHOT_DIST = 25;
	
	
	public ArcherKit() {
		// Set kit content items
		setupKitContents();
	}
	
	private void setupKitContents() {
		ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("Archer Sword");
		swordMeta.spigot().setUnbreakable(true);
		sword.setItemMeta(swordMeta);
		
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName("Archer Bow");
		bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, false);
		bowMeta.spigot().setUnbreakable(true);
		bow.setItemMeta(bowMeta);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 4);
		
		ItemStack arrowStack1 = new ItemStack(Material.ARROW, 64);
		ItemStack arrowStack2 = new ItemStack(Material.ARROW, 64);
		
		ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("Archer Helmet");
		helmetMeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName("Archer Chestplate");
		chestplateMeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName("Archer Leggings");
		leggingsMeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName("Archer Boots");
		bootsMeta.spigot().setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		
		this.kitContents = new ItemStack[]{sword, bow, steak, arrowStack1, arrowStack2, helmet, chestplate, leggings, boots};
	}
	
	public void setupPlayerInventory(Inventory playerInv) {
		
		playerInv.clear();
		

		if (FBMCCTF.getMode() ==  1) {
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(2, getKitContents()[2]);
			playerInv.setItem(16, getKitContents()[3]);
			playerInv.setItem(17, getKitContents()[4]);
			playerInv.setItem(39, getKitContents()[5]);
			playerInv.setItem(38, getKitContents()[6]);
			playerInv.setItem(37, getKitContents()[7]);
			playerInv.setItem(36, getKitContents()[8]);
		}
		else if (FBMCCTF.getMode() == 0) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			playerInv.setItem(0, getKitContents()[0]);
			playerInv.setItem(1, getKitContents()[1]);
			playerInv.setItem(16, getKitContents()[3]);
			playerInv.setItem(17, getKitContents()[4]);
			playerInv.setItem(39, getKitContents()[5]);
			playerInv.setItem(38, getKitContents()[6]);
			playerInv.setItem(37, getKitContents()[7]);
			playerInv.setItem(36, getKitContents()[8]);

			for (int i = 2; i < 36; i++) {
				if (i == 16 || i == 17) {
					i = 18;
				}
				playerInv.setItem(i, soup);
			}
		}

		
	}
	
	
	@EventHandler
	public void onArcherArrowHit(EntityDamageByEntityEvent event) {
		
		if (event.isCancelled()) {
			return;
		}

		Entity damager = event.getDamager();
		Entity entity = event.getEntity();
		
		if (damager instanceof Arrow) {
			Arrow arrow = (Arrow) damager;
			Entity entityShooter = (Entity) arrow.getShooter();
			
			
			Bukkit.getLogger().info("[FBMCCTF] Archer Instakill got to first if");
			
			if (entityShooter instanceof Player && entity instanceof Player) {
				Player shooter = (Player) entityShooter;
				Player hitPlayer = (Player) entity;
				
				CTFPlayer currentCTFPlayer = FBMCCTF.getCtfPlayers(shooter);
				
				if (currentCTFPlayer.getChosenKit() != CTFKit.ARCHER) {
					event.setCancelled(true);
					return;
				}
				
				Bukkit.getLogger().info("[FBMCCTF] Archer Instakill got to second if");
				
				int distanceBetweenPlayers = (int) hitPlayer.getLocation().distance(shooter.getLocation());
				
				if (distanceBetweenPlayers > HEADSHOT_DIST) {
					hitPlayer.sendMessage("You were shot by " + shooter.getDisplayName() + " from " + distanceBetweenPlayers + " blocks!");
					shooter.sendMessage("You shot " + hitPlayer.getDisplayName() + " from " + distanceBetweenPlayers + " blocks!");
					hitPlayer.setHealth(0.0);
					Bukkit.getLogger().info("[FBMCCTF] Archer Instakill got to third if");
				}
			}
		}
		else {
			Bukkit.getLogger().warning("[FBMCCTF] Archer Instakill didnt work!");
		}
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


	

}
