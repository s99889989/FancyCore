package com.daxton.fancycore.listener;

import com.daxton.fancycore.api.entity.BukkitAttributeSet;
import com.daxton.fancycore.api.hook.ProtocolSupport.SetClientVersion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler//當玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        //清除玩家有關屬性
        BukkitAttributeSet.removeAllAttribute(player);
        //檢測玩家版本，並記錄
        SetClientVersion.toMap(player);

    }

    @EventHandler//玩家登出
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();


    }



}
