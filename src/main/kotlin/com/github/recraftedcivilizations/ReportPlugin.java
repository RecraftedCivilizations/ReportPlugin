package com.github.recraftedcivilizations

import java.util.Set;

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;



public class BugReport
  extends ARecraftedPlugin
{
    public BugReport() {}

    public void onEnable() {}

    public void onDisable() {}

    // Here is smth missing, Take a look at the first argument
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        // Here u see what is missing
        Player player = (Player)sender;

        FileConfiguration config = getConfig();

        // One bracket to much
        if ((cmd.getName().equalsIgnoreCase("bugreport")) && ((sender instanceof Player)))
        {
            // length not lenght
          if (args.length >= 1)
          {
            String bugMessage = "";

            // Idk what exactly is wrong here but intellij complains
            for(String arg:args) {
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
            // You have a 0 instead of an O
          if (player.isOp())
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

        // Check your comparison string
       if ((cmd.getName().equalsIgnoreCase("delbug")) && ((sender instanceof Player)))
       {
           // Again 0 instead of O
        if ((args.length == 1) && (player.isOp()))
        {
            // Idk what you are trying to do but this is wrong
            // And you have one bracket to much
         if (config.contains(args[0].toLowerCase()))
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