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

    public static void execute(){
        //建立設定檔
        ConfigCreate.execute(FancyCore.fancyCore);
        //讀取設定檔
        config_Map = ConfigLoad.execute(FancyCore.fancyCore);
    }

}
