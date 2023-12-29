package net.flashbackmc.ctf;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.flashbackmc.ctf.commands.CommandListeners;
import net.flashbackmc.ctf.gui.KitGUI;
import net.flashbackmc.ctf.kits.ArcherKit;
import net.flashbackmc.ctf.kits.BaseKit;
import net.flashbackmc.ctf.kits.MedicKit;
import net.flashbackmc.ctf.kits.NinjaKit;
import net.flashbackmc.ctf.kits.SoldierKit;
import net.flashbackmc.ctf.listeners.BreakPlaceBlockListener;
import net.flashbackmc.ctf.listeners.KitGUIListener;
import net.flashbackmc.ctf.listeners.PlayerJoinQuitListener;
import net.flashbackmc.ctf.listeners.PlayerRespawnListener;

//TODO
//Soup doesnt disappear on first use
//Medic webs dont work in soup mode

public class FBMCCTF extends JavaPlugin {
	
	private static FBMCCTF plugin;
	
	private static HashMap<Player, CTFPlayer> ctfPlayers = new HashMap<Player, CTFPlayer>();
	
	//Mode for the plugin, if its 1 its set to play CTF
	//If its 0 its set to play normal Soup PvP with the CTF kits
	//Setting to soup mode replaces steak will a full inventory of Mushroom soup.
	private static int mode = 1;

	public FBMCCTF() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("[FBMCCTF] The plugin has been successfully enabled!");
		
		setupConfig();
		
		registerCommands();
		registerListeners();
		plugin = this;
	}
	
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("[FBMCCTF] The plugin has been successfully disabled!");
        }

	private void setupConfig() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();
	}
	
	private void registerCommands() {

		CommandListeners commands = new CommandListeners();

		getCommand("class").setExecutor(new KitGUI());
		getCommand("mode").setExecutor(commands);
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new KitGUIListener(), this);
		getServer().getPluginManager().registerEvents(new ArcherKit(), this);
		getServer().getPluginManager().registerEvents(new SoldierKit(), this);
		getServer().getPluginManager().registerEvents(new MedicKit(), this);
		getServer().getPluginManager().registerEvents(new NinjaKit(), this);
		getServer().getPluginManager().registerEvents(new BaseKit(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
		getServer().getPluginManager().registerEvents(new CTFPlayer(), this);
		getServer().getPluginManager().registerEvents(new BreakPlaceBlockListener(), this);
	}

	/**
	 * @return the ctfPlayers
	 */
	public static HashMap<Player, CTFPlayer> getCtfPlayers() {
		return ctfPlayers;
	}
	
	/**
	 * @param ctfPlayer the ctfPlayers to set
	 */
	public static void setCtfPlayers(HashMap<Player, CTFPlayer> ctfPlayer) {
		FBMCCTF.ctfPlayers = ctfPlayer;
	}
	
	/**
	 * @param ctfPlayer the ctfPlayer to get
	 * @return 
	 */
	public static CTFPlayer getCtfPlayers(Player player) {
		return FBMCCTF.ctfPlayers.get(player);
	}
	
	/**
	 * @param ctfPlayer the ctfPlayer to add
	 */
	public static void addCtfPlayer(Player player, CTFPlayer ctfPlayer) {
		FBMCCTF.ctfPlayers.put(player, ctfPlayer);
	}
	
	/**
	 * @param player the player to remove
	 * @return 
	 */
	public static void removeCtfPlayer(Player player) {
		FBMCCTF.ctfPlayers.remove(player);
	}
	
	public static void setMode(int modeToSet) {
		mode = modeToSet;
	}

	public static int getMode() {
		return mode;
	}
	
	
	/**
	 * @return the plugin
	 */
	public static FBMCCTF getPlugin() {
		return plugin;
	}
}
