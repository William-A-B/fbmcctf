package net.flashbackmc.ctf.kits;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.flashbackmc.ctf.FBMCCTF;

public class HeavyKit extends BaseKit {

	private ItemStack[] kitContents;
	
	public HeavyKit() {
		// Set kit content items
		setupKitContents();
	}

	
	private void setupKitContents() {
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName("Heavy Sword");
		swordMeta.spigot().setUnbreakable(true);
		sword.setItemMeta(swordMeta);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 3);
		
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName("Heavy Helmet");
		helmetMeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta chestplateMeta = chestplate.getItemMeta();
		chestplateMeta.setDisplayName("Heavy Chestplate");
		chestplateMeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName("Heavy Leggings");
		leggingsMeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName("Heavy Boots");
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
