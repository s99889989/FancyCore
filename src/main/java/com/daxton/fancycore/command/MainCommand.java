package com.daxton.fancycore.command;



import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.task.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
        if(sender instanceof Player && !sender.isOp()){
            return true;
        }

        if(args[0].equalsIgnoreCase("reload") && args.length == 1) {
            Reload.execute();
            String reloadString = FileConfig.languageConfig.getString("Language.Reload");
            if(sender instanceof Player && reloadString != null){
                Player player = (Player) sender;
                player.sendMessage(reloadString);
            }
            FancyCore.fancyCore.getLogger().info(reloadString);
        }

        return true;
    }

}
