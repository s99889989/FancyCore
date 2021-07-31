package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.config.SearchConfigFile;
import com.daxton.fancycore.api.config.SearchConfigMap;
import com.daxton.fancycore.config.FileConfig;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyMap {

    public static Map<String , List<String>> conversion_Key = new ConcurrentHashMap<>();

    public static void setConversionKey(){

        SearchConfigMap.configList(FileConfig.config_Map ,"Character/").forEach(config -> {
            SearchConfigFile.sectionList(config, "").forEach(key -> {
                List<String> actionList = config.getStringList(key+".message");
                conversion_Key.put(key, actionList);
            });
        });

    }

    public static void reload(){
        conversion_Key.clear();
        setConversionKey();
    }

}
