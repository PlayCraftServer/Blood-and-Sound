package me.DJBiokinetix;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Blood extends JavaPlugin implements Listener{
	  
  public void onEnable(){
	  getServer().getPluginManager().registerEvents(this, this);
	  saveDefaultConfig();
	  getLogger().info("Plugin Blood and Sound enabled!");
	  getLogger().info("v1.1 by: DJBiokinetix");
  }
  
  public void onDisable(){
	  getLogger().info("Plugin Blood and Sound disabled!");
	  getLogger().info("v1.1 by: DJBiokinetix");
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	  Player p = (Player)sender;
	  if(label.equalsIgnoreCase("blood")){
		  if(args.length == 0){
			  sender.sendMessage(ChatColor.RED + "Blood by: DJBiokinetix");
			  sender.sendMessage(ChatColor.RED + "Usage: /blood reload");
		  } else if (args.length == 1){
			  if (args[0].equalsIgnoreCase("reload")){
				  if(p.hasPermission("blood.reload")){
					  	reloadConfig();
					  	p.sendMessage(ChatColor.GREEN + "Configuration has been reload!");
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permissions!");
				  }
			  }
		  }
	  }
	  return false;
  }
  
  @EventHandler
  public void bloodEffect(EntityDamageEvent e) {
    if (!(e.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) e.getEntity();
    player.getWorld().playEffect(player.getLocation().add(0.0D, 0.8D, 0.0D), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
  }
 
  @EventHandler
  public void onDeath(PlayerDeathEvent e){
	  String msg = getConfig().getString("Die Message").replaceAll("&", "§");
	  Player p = (Player)e.getEntity();
	  p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1L, 1L);
	  p.sendMessage(ChatColor.STRIKETHROUGH + "----------------");
	  p.sendMessage(msg);
	  p.sendMessage(ChatColor.STRIKETHROUGH + "----------------");
  }
}
