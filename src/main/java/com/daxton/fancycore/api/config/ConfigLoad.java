package com.daxton.fancycore.api.config;

import com.daxton.fancycore.FancyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigLoad {

    //讀取設定檔
    public static Map<String, FileConfiguration> execute(Plugin plugin){
        Map<String, FileConfiguration> configurationMap = new HashMap<>();
        List<File> fileList = SerachFile.findYML(plugin.getDataFolder().getPath());

        for(File file : fileList){
            //FancyCore.fancyCore.getLogger().info(file.getPath());
            String key = file.getPath(); //.replace("\\","/")
            int inKey = plugin.getDataFolder().getPath().length()+1;
            key = key.substring(key.indexOf(plugin.getDataFolder().getPath())+inKey);
            //FancyCore.fancyCore.getLogger().info(key);
            File configFile = new File(plugin.getDataFolder(), key);
            if(key.endsWith(".yml") && configFile.exists()){
                FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
                configurationMap.put(key.replace("\\","/"), fileConfiguration);
            }
        }
        return configurationMap;
    }

    //讀取設定檔(plugin.getDataFolder().getPath()=plugins\FancyCore)
    public static Map<String, FileConfiguration> execute(String headPath){
        Map<String, FileConfiguration> configurationMap = new HashMap<>();
        List<File> fileList = SerachFile.findYML(headPath);

        for(File file : fileList){
            //FancyCore.fancyCore.getLogger().info(file.getPath());
            String key = file.getPath(); //.replace("\\","/")
            int inKey = headPath.length()+1;
            key = key.substring(key.indexOf(headPath)+inKey);
            //FancyCore.fancyCore.getLogger().info(key);
            File configFile = new File(headPath, key);
            if(key.endsWith(".yml") && configFile.exists()){
                FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
                configurationMap.put(key.replace("\\","/"), fileConfiguration);
            }
        }
        return configurationMap;
    }

}
