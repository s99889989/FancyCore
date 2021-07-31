package com.daxton.fancycore.api.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class SearchConfigFile {

    //搜尋設定檔內的關鍵字ConfigurationSection
    public static List<String> sectionList(FileConfiguration fileConfiguration, String key){
        List<String> keyList = new ArrayList<>();

        if(fileConfiguration.getConfigurationSection(key) != null){
            keyList.addAll(fileConfiguration.getConfigurationSection(key).getKeys(false));
        }

        return keyList;
    }

}
