package me.bolopaul.mctesta04;

import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.command.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;

public class EscalatorMovement implements Listener {
	
	private static int EscBkType = -1;
	private static Material[] escbklist = {Material.DIAMOND_BLOCK , Material.EMERALD_BLOCK, Material.GOLD_BLOCK, Material.IRON_BLOCK};
	private static BlockFace[] facelist = {BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH};
	public static Location ploc1 ;
	public static Location ploc2 ;
	public static Location ploc3 ;
	//public static int standonstairs = 0;
	//public static Tag<Material> stairs;
	
	public static boolean escbkdetect (CommandSender sender) {
		
		Player user = (Player) sender;
		ploc1 = user.getLocation().clone();
		Location locstandptblock = (user.getLocation().subtract(0, 1, 0));
		//ploc3 = (locstandptblock.add(0, 1, 0));
		Location locstandptblockm1 = (user.getLocation().subtract(0, 2, 0));
		//BlockData standptblock = locstandptblock.getBlock().getBlockData();
		BlockData standptblockm1 = locstandptblockm1.getBlock().getBlockData();
		//Material unbk1 = standptblock.getMaterial();
		Material unbk2 = standptblockm1.getMaterial();
		//String standptblockm1str = standptblockm1.toString();
		//sender.sendMessage(user.getLocation().getBlock().toString());
		//sender.sendMessage(standptblockm1.toString());
		/*if (Tag.STAIRS.isTagged(ploc3.getBlock().getType())) {
			standonstairs = 1;
			//sender.sendMessage(ChatColor.GREEN + "standonstairs = " + standonstairs);
		} else {
			standonstairs = 0;
			//sender.sendMessage(ChatColor.RED + "standonstairs = " + standonstairs);
		}*/
		
		for (int i=0; i < (escbklist.length); i++) {
			if (unbk2 == escbklist[i]) {
				EscBkType = i;
				sender.sendMessage(ChatColor.AQUA + "EscBkType = " + EscBkType + " (" + escbklist[i] + ")");
				escmove(sender);
			} else EscBkType = -1;
		}
		
		if ((EscBkType < 0) || (EscBkType > 3)) {
			//sender.sendMessage("The block is not match to the esc block type.");
			
		} else /*if ((Tag.STAIRS.isTagged(ploc3.getBlock().getType())) == true)*/{
			      escmove(sender);
			      return true;
	    }
		return false;
		
	}

	public static boolean escmove(CommandSender sender) {
		if (!(EscBkType == -1)) {
			if (!((EscBkType < 0)||(EscBkType > 3))) {
				//sender.sendMessage("Continue Movement!");
				Player user = (Player) sender;
				ploc1 = user.getLocation().clone(); //Original XYZ
				BlockFace plface = user.getFacing();
				switch ((++EscBkType)) {
				
					case 1:
						//End the movement
						
						break;
					case 2:
						//Upward Movement
						//ploc2.zero();
						ploc2 = ploc1.clone();
						
						if (plface == facelist[0]) {
							ploc2.add(1, 1, 0);
						} else if (plface == facelist[1]) {
							ploc2.add(0, 1, 1);
						} else if (plface == facelist[2]) {
							ploc2.add(0, 1, 0);
							ploc2.subtract(1, 0, 0);
						} else if (plface == facelist[3]) {
							ploc2.add(0, 1, 0);
							ploc2.subtract(0, 0, 1);
						}
						//sender.sendMessage(ploc2.toString());
						sender.sendMessage("Upward 1 Block");
						user.teleport(ploc2);
						break;
					case 3:
						//Downward Movement
						//ploc2.zero();
						ploc2 = ploc1.clone();
						
						if (plface == facelist[0]) {
							ploc2.subtract(0, 1, 0);
							ploc2.add(1, 0, 0);
						} else if (plface == facelist[1]) {
							ploc2.subtract(0, 1, 0);
							ploc2.add(0, 0, 1);
						} else if (plface == facelist[2]) {
							ploc2.subtract(1, 1, 0);
						} else if (plface == facelist[3]) {
							ploc2.subtract(0, 1, 1);
						}
						//sender.sendMessage(ploc2.toString());
						sender.sendMessage("Downward 1 Block");
						user.teleport(ploc2);
						break;
					case 4:
						//Start Movement | Move 1 block forward
						
						break;
				
				}
				return true;
			}
			sender.sendMessage("System Error! Out of range! (" + EscBkType + ")");
			return false;
		}
		return false;
	}
	
}
