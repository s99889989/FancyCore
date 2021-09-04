package com.daxton.fancycore.server;

import com.comphenix.protocol.ProtocolLibrary;
import com.daxton.fancycore.api.character.stringconversion.KeyMap;
import com.daxton.fancycore.manager.ProtocolMap;

import com.daxton.fancycore.other.customvalue.SetCustomValue;
import com.daxton.fancycore.other.taskaction.StringToMap;

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
        //自訂值
        SetCustomValue.execute();
    }

}
