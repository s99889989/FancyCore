package com.daxton.fancycore.command;

import com.daxton.fancycore.FancyCore;
import static com.daxton.fancycore.config.FileConfig.languageConfig;

import com.daxton.fancycore.api.gui.item.GuiEditItem;
import com.daxton.fancycore.gui.MainOpen;
import com.daxton.fancycore.nms.NMSItem;
import com.daxton.fancycore.other.task.CopyClipboard;
import com.daxton.fancycore.server.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    public static String itemString = "";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
        if(sender instanceof Player && !sender.isOp()){
            return true;
        }

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("item")){
                    if(args[1].equalsIgnoreCase("type")){
                        String itemType = player.getInventory().getItemInMainHand().getType().toString();
                        player.sendMessage(itemType);
                        CopyClipboard.copy(itemType);
                    }
                    if(args[1].equalsIgnoreCase("mmoitemtype")){
                        ItemStack itemStack = player.getInventory().getItemInMainHand();
                        player.sendMessage(GuiEditItem.mmoItemType(itemStack));
                    }
                }


            }

            if(args.length == 1 && args[0].equalsIgnoreCase("gui")) {
                MainOpen.open(player);
            }
            if(args.length == 1 && args[0].equalsIgnoreCase("hand")) {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                ItemStack[] itemStacks = new ItemStack[]{itemStack};
                itemString = NMSItem.itemStackToBase64(itemStacks);
                player.sendMessage(itemString);
            }
            if(args.length == 1 && args[0].equalsIgnoreCase("hand2")) {
                ItemStack[] itemStacks = NMSItem.base64toItemStack(itemString);
                for(ItemStack itemStack : itemStacks){
                    String s = itemStack.getI18NDisplayName();
                    player.sendMessage(s);
                }
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Reload.execute();
            if(sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(languageConfig.getString("OpMessage.Reload")+"");
            }
            FancyCore.fancyCore.getLogger().info(languageConfig.getString("LogMessage.Reload")+"");
        }

        return true;
    }

}
