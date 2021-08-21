package com.daxton.fancycore.api.config;

import com.daxton.fancycore.FancyCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchConfigMap {

    //丟入設定檔Map和過濾類型、設定是否包含.yml，回傳檔案名稱名稱List
    //依照關鍵字搜尋有關類別的文件名稱
    public static List<String> fileNameList(Map<String, FileConfiguration> config_Map, String key, boolean cutYML){
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
    //丟入設定檔Map和過濾類型、設定是否包含.yml，回傳檔案路徑名稱List
    public static List<String> filePathList(Map<String, FileConfiguration> config_Map, String key, boolean cutYML){
        List<String> nameList = new ArrayList<>();
        if(key == null){
            return nameList;
        }
        config_Map.forEach((s, fileConfiguration) -> {
            if(!key.isEmpty()){
                if(s.startsWith(key)){
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

    //依照關鍵字搜尋有關類別的文件
    public static List<FileConfiguration> configList(Map<String, FileConfiguration> config_Map, String key){
        List<FileConfiguration> configList = new ArrayList<>();
        if(key == null){
            return configList;
        }
        config_Map.forEach((s, fileConfiguration) -> {
            if(!key.isEmpty()){
                if(s.startsWith(key)){
                    configList.add(fileConfiguration);
                }
            }else {
                configList.add(fileConfiguration);
            }
        });
        return configList;
    }

}
