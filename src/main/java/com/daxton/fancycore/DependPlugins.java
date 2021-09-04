package com.daxton.fancycore;

import com.daxton.fancycore.listener.PackListener;
import com.daxton.fancycore.manager.OtherManager;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import static com.daxton.fancycore.config.FileConfig.languageConfig;

public class DependPlugins {

    public static boolean depend(){

        FancyCore fancyCore = FancyCore.fancyCore;
        if(Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib") != null && Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")){
            fancyCore.getLogger().info(languageConfig.getString("LogMessage.LoadProtocolLib"));
            Bukkit.getPluginManager().registerEvents(new PackListener(),FancyCore.fancyCore);
        }else {
            languageConfig.getStringList("LogMessage.UnLoadProtocolLib").forEach(s->fancyCore.getLogger().info(s));
            return false;
        }

        if(Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            fancyCore.getLogger().info(languageConfig.getString("LogMessage.LoadPlaceholderAPI"));
        }else {
            languageConfig.getStringList("LogMessage.UnLoadPlaceholderAPI").forEach(s->fancyCore.getLogger().info(s));
            return false;
        }

        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null && Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            fancyCore.getLogger().info(languageConfig.getString("LogMessage.LoadVault"));
            RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp != null) {
                OtherManager.econ = rsp.getProvider();
            }
        }

        return true;
    }


}
