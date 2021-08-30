package com.daxton.fancycore.listener;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.manager.PlayerManagerCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.UUID;

public class InventoryListener implements Listener {

    @EventHandler//當使用背包時
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)){
            return;
        }
        //新監聽
        ClickType clickType = event.getClick();
        InventoryAction action = event.getAction();

        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        GUI gui = PlayerManagerCore.player_Gui_Map.get(uuid);
        if(gui != null && gui.isOpen()){
            event.setCancelled(!gui.isMove());
            int slot = event.getRawSlot();
            if(slot >= 0){
                if(gui.buttons[slot] != null){
                    event.setCancelled(!gui.buttons[slot].move);
                    if(gui.buttons[slot].guiAction != null){
                        gui.buttons[slot].guiAction.execute(clickType, action, slot);
                    }

                }
            }

        }
    }

    @EventHandler//關閉背包
    public void onInventoryClose(InventoryCloseEvent event){
        //新監聽
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        GUI gui = PlayerManagerCore.player_Gui_Map.get(uuid);
        if(gui != null && !gui.isOpen()){
            gui.setOpen(false);
            if(gui.guiCloseAction != null){
                gui.guiCloseAction.execute();
            }
        }
    }

    //當玩家聊天
    @EventHandler
    public void onChat(PlayerChatEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        GUI gui = PlayerManagerCore.player_Gui_Map.get(uuid);
        if(gui != null && gui.isChat()){
            event.setCancelled(true);
            gui.setChat(false);
            gui.guiChatAction.execute(player, event.getMessage());
        }

    }

}
