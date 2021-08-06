package com.daxton.fancycore.api.hook.ProtocolSupport;

import com.daxton.fancycore.manager.PlayerManager;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;

public class SetClientVersion {

    public static void toMap(Player player){
        String uuidString = player.getUniqueId().toString();
        //存入客戶端版本
        if (Bukkit.getServer().getPluginManager().getPlugin("ProtocolSupport") != null){
            protocolsupport.api.ProtocolVersion protocolVersion = ProtocolSupportAPI.getProtocolVersion(player);
            //player.sendMessage(NMSVersion.clientVersion(protocolVersion.getId()));
            PlayerManager.player_Version_Map.put(uuidString, NMSVersion.clientVersion(protocolVersion.getId()));
        }else {
            //player.sendMessage(NMSVersion.clientVersion(ProtocolMap.protocolManager.getProtocolVersion(player)));
            PlayerManager.player_Version_Map.put(uuidString, NMSVersion.clientVersion(ProtocolMap.protocolManager.getProtocolVersion(player)));
        }
    }

}
