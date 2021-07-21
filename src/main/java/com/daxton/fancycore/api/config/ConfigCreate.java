package com.daxton.fancycore.api.config;

import com.daxton.fancycore.FancyCore;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigCreate {

    public static FancyCore fancyCore = FancyCore.fancyCore;

    //執行建立設定檔
    public static void execute(Plugin plugin){
        String pluginPatch = plugin.getClass().getResource("").getPath();
        pluginPatch = pluginPatch.substring(pluginPatch.indexOf("plugins/"), pluginPatch.indexOf(".jar!"));
        File testFile = new File(pluginPatch +".jar");
        try {
            List<String> testList = SerachZip.findYML(testFile.toString());
            for(String patch : testList){
                //建立.yml設定檔
                ConfigCreate.create(plugin, patch);
            }
        }catch (Exception exception){
            //
        }


    }

    //建立.yml設定檔
    public static void create(Plugin plugin, String filePatch){
        File file = new File(plugin.getDataFolder(), filePatch.replace("resource/",""));
        if(!file.exists()){
            fancyCore.saveResource(plugin, filePatch, filePatch.replace("resource/",""), false);
        }
    }


}
