package me.bolopaul.mctesta04;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class MCTestA04 extends JavaPlugin implements Listener, CommandExecutor {

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MCTestA04 is enabling...");
		getConfig();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MCTestA04 is enabled !");
		getServer().getPluginManager().registerEvents(new EscalatorMovement(), this);
		runnable();
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "MCTestA04 is disabling...");
		saveConfig();
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "MCTestA04 is disabled !");

	}

	/*@Override
	public void onLoad() {
		
	}*/
	
	public void runnable() {
		new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					EscalatorMovement.escbkdetect(all);
				}
				
			}
			
		}.runTaskTimer(this, 0, 1);
	}
	
	//@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("fuck")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("fuck.use")) {
					String user = ((Player) sender).getName();
					player.sendMessage("STtraveller wants to fuck "+ user +" hard!");
					return true;
				}
				player.sendMessage("You do not have permission to issue the command.");
				return true;
				
			} else {
				sender.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "This command can only issued by player.");
				return true;
				
			}
		}
		
		if (label.equalsIgnoreCase("a04reloadconfig")) {
			Player player = (Player) sender;
			if (player.isOp() && player.hasPermission("a04reloadconfig.use")) {
				reloadConfig();
				player.sendMessage(ChatColor.YELLOW + "The config of MCTestA04 is reloaded.");
				return true;
			}
			player.sendMessage(ChatColor.RED + "You do not have permission to issue the command.");
			return true;
		}
		
		if (label.equalsIgnoreCase("escdetect")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.isOp() && player.hasPermission("escdetect.use")) {
					EscalatorMovement.escbkdetect(sender);
					player.sendMessage(ChatColor.GRAY + "Issued Escalator Block Detection !");
					return true;
				}
			}
		}
		
		/*if (label.equalsIgnoreCase("timer")) {
			
		}*/
		
		return false;
	}
	
}
