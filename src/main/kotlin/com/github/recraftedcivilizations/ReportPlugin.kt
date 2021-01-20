package com.github.recraftedcivilizations

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class BugReport
  extends JavaPlugin
{
    public BugReport() {}

    public void onEnable() {}

    public void onDisable() {}

    public boolean onCommand((CommandSender, Command cmd, String label, String[] args)
    {
        Player player = (Player)sender;

        FileConfiguration config = getConfig();
        String arg;
        if ((cmd.getName().equalsIgnoreCase("bugreport")) && (sender instanceof Player)))
        {
          if (args.lenght >= 1)
          {
            String bugMessage = "";

            for (arg : args) {
              bugMessage = bugMessage + arg + " ";
            }


            if (!config.contains(player.getName().toLowerCase())) {
              config.set(player.getName().toLowerCase(), bugMessage);
              player.sendMessage(ChatColor.GREEN + "Bug reported successfully!");
              saveConfig();
            } else { player.sendMessage(ChatColor.RED + "Bug alredy submitted!");
            }
          } else { player.sendMessage(ChatColor.RED + "Enter your bugreport after the /bugreport command!");
          }
          return true;
        }



        if ((cmd.getName().equalsIgnoreCase("bugs")) && ((sender instanceof Player)))
        {
          if (player.is0p())
          {

           for (String key : config.getKeys(false)) {
             player.sendMessage(ChatColor.YELLOW + key + ": " + config.getString(key));
          }

          if (config.getKeys(false).isEmpty()) {
             player.sendMessage(ChatColor.GREEN + "No bugs found!");
          }
        } else {
          player.sendMessage(ChatColor.RED + "You must be an op to use this command!");
        }

        return true;
       }


       if ((cmd.getName().equalsIgnoreCase("delbug")) && ((sender instanceof Player)))
       {
        if ((args.length == 1) && (player.is0p()))
        {
         if (config.contains[0].toLowerCase()))
         {
          config.set(args[0].toLowerCase(), null);
          saveConfig();
          player.sendMessage(ChatColor.GREEN + "Bug deleted successfully!");
        } else {
          player.sendMessage(ChatColor.RED + "Player not found!");
        }
      } else { player.sendMessage(ChatColor.RED + "incorrect arguments / invalid permission");
      }
      return true;
    }

    return false;
  }
}