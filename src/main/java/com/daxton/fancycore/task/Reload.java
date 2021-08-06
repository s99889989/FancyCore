package com.daxton.fancycore.task;

import com.daxton.fancycore.api.character.stringconversion.KeyMap;
import com.daxton.fancycore.api.hook.MythicMob.MobConfig;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.OtherManager;
import discord4j.core.DiscordClientBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class Reload {

    //重新讀取的一些任務
    public static void execute(){
        //重新取設定檔
        FileConfig.reload();
        //建立怪物設定檔
        MobConfig.reload();
        //設定DC連接
        FileConfiguration fileConfig = FileConfig.config_Map.get("config.yml");
        boolean discordEnable = fileConfig.getBoolean("Discord.enable");
        if(discordEnable){
            String token = fileConfig.getString("Discord.token");
            if(token != null){
                OtherManager.client = DiscordClientBuilder.create(token)
                        .build()
                        .login()
                        .block();
            }
        }
        //重新設置轉換字串
        KeyMap.reload();
        //
    }

}
