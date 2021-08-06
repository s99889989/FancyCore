package com.daxton.fancycore.api.config;

import com.daxton.fancycore.FancyCore;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigCreate {

    public static FancyCore fancyCore = FancyCore.fancyCore;

    //執行建立設定檔
    public static void execute(Plugin plugin){
        try {
            List<String> testList = SerachZip.findFile(getPluginJarName(plugin) +".jar", "resource/", "", true);
            for(String patch : testList){
                //FancyCore.fancyCore.getLogger().info(patch);
                //建立.yml設定檔
                //ConfigCreate.create(plugin, patch);
                String savePath = patch.replace("resource/", "");
                FileCopy.resourceCopy(plugin, patch, savePath, false);
            }
        }catch (Exception exception){
            //
        }
    }
    //獲取插件.jar名稱
    public static String getPluginJarName(Plugin plugin){
        String out;
        String pluginPatch = plugin.getClass().getResource("").getPath();
        out = pluginPatch.substring(pluginPatch.indexOf("plugins/"), pluginPatch.indexOf(".jar!"));
        return out;
    }


}
