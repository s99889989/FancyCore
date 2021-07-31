package com.daxton.fancycore.listener;

import com.daxton.fancycore.api.entity.BukkitAttributeSet;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GUISlotItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler//當玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        BukkitAttributeSet.removeAllAttribute(player);
    }

    @EventHandler//當使用背包時
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)){
            return;
        }
        GUISlotItem.onInventoryClick(event);
    }

    @EventHandler//關閉背包
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        GUISlotItem.onInventoryClose(event);
    }

}
