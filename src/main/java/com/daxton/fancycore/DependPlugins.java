package com.daxton.fancycore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DependPlugins {

    public static boolean depend(){

        FancyCore fancyCore = FancyCore.fancyCore;

        if (Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib") != null && Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")){
            fancyCore.getLogger().info(ChatColor.GREEN+"Loaded ProtocolLib");
        }else {
            fancyCore.getLogger().severe("*** ProtocolLib is not installed or not enabled. ***");
            fancyCore.getLogger().severe("*** FancyCore will be disabled. ***");
            fancyCore.getLogger().severe("*** ProtocolLib未安裝或未啟用。 ***");
            fancyCore.getLogger().severe("*** FancyCore將被卸載。 ***");
            return false;
        }

        if (Bukkit.getServer().getPluginManager().getPlugin("HolographicDisplays") != null && Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            fancyCore.getLogger().info(ChatColor.GREEN+"Loaded HolographicDisplays");
        }else {
            fancyCore.getLogger().severe("*** HolographicDisplays is not installed or not enabled. ***");
            fancyCore.getLogger().severe("*** FancyCore will be disabled. ***");
            fancyCore.getLogger().severe("*** HolographicDisplays未安裝或未啟用。 ***");
            fancyCore.getLogger().severe("*** FancyCore將被卸載。 ***");
            return false;
        }

        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            fancyCore.getLogger().info(ChatColor.GREEN+"Loaded PlaceholderAPI");
        }else {
            fancyCore.getLogger().severe("*** PlaceholderAPI is not installed or not enabled. ***");
            fancyCore.getLogger().severe("*** FancyCore will be disabled. ***");
            fancyCore.getLogger().severe("*** PlaceholderAPI未安裝或未啟用。 ***");
            fancyCore.getLogger().severe("*** FancyCore將被卸載。 ***");
            return false;
        }

        return true;
    }

}
