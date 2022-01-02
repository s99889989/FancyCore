package com.daxton.fancycore.hook.ProtocolSupport;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;

public class SetClientVersion {

    public static String toMap(Player player){
        //存入客戶端版本
        if (Bukkit.getServer().getPluginManager().getPlugin("ProtocolSupport") != null){
            protocolsupport.api.ProtocolVersion protocolVersion = ProtocolSupportAPI.getProtocolVersion(player);
            return NMSVersion.clientVersion(protocolVersion.getId());
        }else {
            return NMSVersion.clientVersion(ProtocolMap.protocolManager.getProtocolVersion(player));
        }

    }

}
