package com.daxton.fancycore.task.server;

import com.comphenix.protocol.ProtocolLibrary;
import com.daxton.fancycore.api.character.stringconversion.KeyMap;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.OtherManager;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.task.location.Sound;
import com.daxton.fancycore.task.player.Title;
import discord4j.core.DiscordClientBuilder;
import org.bukkit.configuration.file.FileConfiguration;

public class Start {

    //只在開服時執行的任務
    public static void execute(){

        //ProtocolLib
        ProtocolMap.protocolManager = ProtocolLibrary.getProtocolManager();
        //動作
//        TaskActionManager.task_Action_Map.put("sound", new Sound());
//        TaskActionManager.task_Action_Map.put("title", new Title());
        //設定DC連接
//        FileConfiguration fileConfig = FileConfig.config_Map.get("config.yml");
//        boolean discordEnable = fileConfig.getBoolean("Discord.enable");
//        if(discordEnable){
//            String token = fileConfig.getString("Discord.token");
//            if(token != null){
//                OtherManager.client = DiscordClientBuilder.create(token)
//                        .build()
//                        .login()
//                        .block();
//            }
//        }
        //設置轉換字串
        KeyMap.setConversionKey();
        //設置動作Map
        StringToMap.setActionMap();
    }

}
