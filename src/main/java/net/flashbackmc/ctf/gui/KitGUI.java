package net.flashbackmc.ctf.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;


public class KitGUI implements CommandExecutor {

	private final ItemStack heavyItem = new ItemStack(Material.DIAMOND_SWORD, 1);
	private final ItemStack soldierItem = new ItemStack(Material.IRON_SWORD, 1);
	private final ItemStack medicItem = new ItemStack(Material.GOLD_SWORD, 1);
	private final ItemStack archerItem = new ItemStack(Material.BOW, 1);
	
	private final ItemStack assassinItem = new ItemStack(Material.REDSTONE, 1);
	private final ItemStack chemistItem = new ItemStack(Material.POTION, 1);
	private final ItemStack dwarfItem = new ItemStack(Material.EXP_BOTTLE, 1);
	private final ItemStack elfItem = new ItemStack(Material.SAPLING, 1);
	private final ItemStack engineerItem = new ItemStack(Material.CAKE, 1);
	
	private final ItemStack mageItem = new ItemStack(Material.DIAMOND_HOE, 1);
	private final ItemStack necroItem = new ItemStack(Material.NAME_TAG, 1);
	private final ItemStack ninjaItem = new ItemStack(Material.ENDER_PEARL, 1);
	private final ItemStack pyroItem = new ItemStack(Material.FLINT_AND_STEEL, 1);
	private final ItemStack scoutItem = new ItemStack(Material.IRON_BOOTS, 1);
	
	private final ItemStack fashionistaItem = new ItemStack(Material.FIREWORK, 1);
	
	
	public KitGUI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("fbmcctf.kit")) {
				Inventory classSelectorInvetory = Bukkit.createInventory(p, 54, "Class Selector");
				
				setInventoryContents();
				
				classSelectorInvetory.setItem(10, heavyItem);
				classSelectorInvetory.setItem(12, soldierItem);
				classSelectorInvetory.setItem(14, medicItem);
				classSelectorInvetory.setItem(16, archerItem);
				classSelectorInvetory.setItem(27, assassinItem);
				classSelectorInvetory.setItem(29, chemistItem);
				classSelectorInvetory.setItem(31, dwarfItem);
				classSelectorInvetory.setItem(33, elfItem);
				classSelectorInvetory.setItem(35, engineerItem);
				classSelectorInvetory.setItem(36, mageItem);
				classSelectorInvetory.setItem(38, necroItem);
				classSelectorInvetory.setItem(40, ninjaItem);
				classSelectorInvetory.setItem(42, pyroItem);
				classSelectorInvetory.setItem(44, scoutItem);
				classSelectorInvetory.setItem(49, fashionistaItem);
				
				p.openInventory(classSelectorInvetory);
			}
			else {
				p.sendMessage(ChatColor.RED + "You don't have permission!");
			}
			
		}
		
		
		return true;
	}
	
	private void setInventoryContents() {
		
		ItemMeta heavyMeta = heavyItem.getItemMeta();
		heavyMeta.setDisplayName("Heavy");
		heavyItem.setItemMeta(heavyMeta);
		
		ItemMeta soldierMeta = soldierItem.getItemMeta();
		soldierMeta.setDisplayName("Soldier");
		soldierItem.setItemMeta(soldierMeta);
		
		ItemMeta medicMeta = medicItem.getItemMeta();
		medicMeta.setDisplayName("Medic");
		medicItem.setItemMeta(medicMeta);
		
		ItemMeta archerMeta = archerItem.getItemMeta();
		archerMeta.setDisplayName("Archer");
		archerItem.setItemMeta(archerMeta);
		
		ItemMeta assassinMeta = assassinItem.getItemMeta();
		assassinMeta.setDisplayName("Assassin");
		assassinItem.setItemMeta(assassinMeta);
		
		ItemMeta chemistMeta = chemistItem.getItemMeta();
		chemistMeta.setDisplayName("Chemist");
		chemistItem.setItemMeta(chemistMeta);

		ItemMeta dwarfMeta = dwarfItem.getItemMeta();
		dwarfMeta.setDisplayName("Dwarf");
		dwarfItem.setItemMeta(dwarfMeta);

		ItemMeta elfMeta = elfItem.getItemMeta();
		elfMeta.setDisplayName("Elf");
		elfItem.setItemMeta(elfMeta);

		ItemMeta engineerMeta = engineerItem.getItemMeta();
		engineerMeta.setDisplayName("Engineer");
		engineerItem.setItemMeta(engineerMeta);

		ItemMeta mageMeta = mageItem.getItemMeta();
		mageMeta.setDisplayName("Mage");
		mageItem.setItemMeta(mageMeta);

		ItemMeta necroMeta = necroItem.getItemMeta();
		necroMeta.setDisplayName("Necro");
		necroItem.setItemMeta(necroMeta);

		ItemMeta ninjaMeta = ninjaItem.getItemMeta();
		ninjaMeta.setDisplayName("Ninja");
		ninjaItem.setItemMeta(ninjaMeta);

		ItemMeta pyroMeta = pyroItem.getItemMeta();
		pyroMeta.setDisplayName("Pyro");
		pyroItem.setItemMeta(pyroMeta);

		ItemMeta scoutMeta = scoutItem.getItemMeta();
		scoutMeta.setDisplayName("Scout");
		scoutItem.setItemMeta(scoutMeta);

		ItemMeta fashionistaMeta = fashionistaItem.getItemMeta();
		fashionistaMeta.setDisplayName("Fashionista");
		fashionistaItem.setItemMeta(fashionistaMeta);
		
		
		
	}

}
