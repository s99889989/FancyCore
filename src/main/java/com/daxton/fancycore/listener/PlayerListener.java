package com.daxton.fancycore.listener;

import com.daxton.fancycore.api.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler//當玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        GUI.on_Gui.put(uuidString, false);

    }

    //當打開背包時
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        String uuidString = player.getUniqueId().toString();
        if(GUI.on_Gui.get(uuidString)){
            GUI.onInventoryClick(event);
        }
    }

    //關閉背包
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        GUI.on_Gui.put(uuidString, false);

    }

}
