package com.daxton.fancycore.api.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchConfigFile {

    //依照關鍵字搜尋有關類別的文件
    public static List<String> nameList(Map<String, FileConfiguration> config_Map, String key, boolean cutYML){
        List<String> nameList = new ArrayList<>();
        if(key == null){
            return nameList;
        }
        config_Map.forEach((s, fileConfiguration) -> {
            if(!key.isEmpty()){
                if(s.startsWith(key)){
                    while (s.contains("/")){
                        s = s.substring(s.indexOf("/")+1);
                    }
                    if(cutYML){
                        s = s.replace(".yml","");
                    }
                    nameList.add(s);
                }
            }else {
                nameList.add(s);
            }
        });
        return nameList;
    }

}
