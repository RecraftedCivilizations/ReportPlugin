package com.github.recraftedcivilizations
// A ; is missing, Intellij tries to tell u


// And please just remove the other ReportPlugin.kt file


// This import is unnecessary and can be removed
import java.util.Set;

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


// Please name this class Main
// You need to implement CommandExecutor
// public class Main extends ARecraftedPlugin implements CommandExecutor
public class BugReport
  extends ARecraftedPlugin
{
    public BugReport() {}

    public void onEnable() {
        // You need to register your commands,
        // here is a small tutorial on how to do that https://www.spigotmc.org/wiki/create-a-simple-command/
        // If you have questions or are not sure, rather write me a message then not knowing what you are doing

    }

    public void onDisable() {}

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player player = (Player)sender;

        FileConfiguration config = getConfig();

        if ((cmd.getName().equalsIgnoreCase("bugreport")) && ((sender instanceof Player)))
        {
          if (args.length >= 1)
          {
            String bugMessage = "";

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

       if ((cmd.getName().equalsIgnoreCase("delbug")) && ((sender instanceof Player)))
       {
        if ((args.length == 1) && (player.isOp()))
        {
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

// Looks great until now, if you compile it now it should work
// To compile choose Gradle > Tasks > shadow > shadowJar
// The output jar file will be in build/libs, send me the .jar file in there and I ll upload it