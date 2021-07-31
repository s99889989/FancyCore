package com.daxton.fancycore.config;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.config.ConfigCreate;
import com.daxton.fancycore.api.config.ConfigLoad;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class FileConfig {
    //設定檔地圖
    public static Map<String, FileConfiguration> config_Map = new HashMap<>();

    //語言設定檔
    public static FileConfiguration languageConfig;

    public static void execute(){
        //建立設定檔
        ConfigCreate.execute(FancyCore.fancyCore);
        //讀取設定檔
        config_Map = ConfigLoad.execute(FancyCore.fancyCore);

        //設置語言設定檔
        FileConfiguration resourcePackConfig = FileConfig.config_Map.get("config.yml");
        String nowLanguage = resourcePackConfig.getString("Language");
        languageConfig = FileConfig.config_Map.get("Language/"+nowLanguage+".yml");
        if(languageConfig == null){
            languageConfig = FileConfig.config_Map.get("Language/English.yml");
        }
    }

    //重新讀取設定檔
    public static void reload(){
        config_Map.clear();
        config_Map = ConfigLoad.execute(FancyCore.fancyCore);
        //設置語言設定檔
        FileConfiguration resourcePackConfig = FileConfig.config_Map.get("config.yml");
        String nowLanguage = resourcePackConfig.getString("Language");
        languageConfig = FileConfig.config_Map.get("Language/"+nowLanguage+".yml");
        if(languageConfig == null){
            languageConfig = FileConfig.config_Map.get("Language/English.yml");
        }

    }
}
