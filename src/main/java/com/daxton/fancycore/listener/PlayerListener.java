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
        GUI.on_Gui.put(uuidString, false);
        BukkitAttributeSet.removeAllAttribute(player);
    }

    //當使用背包時
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        String uuidString = player.getUniqueId().toString();
        if(GUI.on_Gui.get(uuidString)){
            GUISlotItem.onInventoryClick(event);
        }
    }

    //關閉背包
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        GUI.on_Gui.put(uuidString, false);
        GUISlotItem.onInventoryClose(event);
    }

}
