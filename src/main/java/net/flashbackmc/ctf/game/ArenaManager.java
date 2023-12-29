package net.flashbackmc.ctf.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import net.flashbackmc.ctf.CTFPlayer;
import net.flashbackmc.ctf.FBMCCTF;

public class ArenaManager {

	private final FBMCCTF plugin;
	private final FileConfiguration config;
	private final List<Arena> arenas;
	
	
	public ArenaManager() {
		this.plugin = FBMCCTF.getPlugin();
		this.config = plugin.getConfig();
		this.arenas = new ArrayList<Arena>();
	}

	public void deserialise() {
		ConfigurationSection configSection = config.getConfigurationSection("Arenas");
		if (configSection == null) {
			return;
		}
		
		configSection.getKeys(false).forEach(s -> arenas.add(new Arena(null, null, null, null, null, null, null, null, 0)));
	}
	
}
