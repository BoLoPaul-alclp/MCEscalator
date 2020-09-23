package me.bolopaul.mctesta04;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import net.md_5.bungee.api.ChatColor;

public class Timer {
	public Scoreboard getboard() {
		ScoreboardManager sbmanager = Bukkit.getScoreboardManager();
		Scoreboard sboard = sbmanager.getNewScoreboard();
		Objective obj1 = sboard.registerNewObjective("Timer1", "timer", ChatColor.translateAlternateColorCodes('&', "&a&lTest Timer"));
		obj1.setDisplaySlot(DisplaySlot.SIDEBAR);
		return sboard;
	}
}