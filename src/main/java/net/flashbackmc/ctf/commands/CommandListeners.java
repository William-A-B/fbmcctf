package net.flashbackmc.ctf.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.flashbackmc.ctf.FBMCCTF;

public class CommandListeners implements CommandExecutor {
    
    public CommandListeners() {

    }

    @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
        if (sender instanceof Player) {
			Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("mode")) {
                if (p.hasPermission("fbmcctf.manager")) {
            
                    if (args[0].equalsIgnoreCase("ctf")) {
                        FBMCCTF.setMode(1);
                        p.sendMessage("FBMCCTF mode has been set to CTF");
                    }
                    else if (args[0].equalsIgnoreCase("soup")) {
                        FBMCCTF.setMode(0);
                        p.sendMessage("FBMCCTF mode has been set to Soup");
                    }
                    else {
                        p.sendMessage("Please specify a mode, e.g. 'ctf' or 'soup'");
                    }
                }
            }
        }
    
        return true;
    }
}
