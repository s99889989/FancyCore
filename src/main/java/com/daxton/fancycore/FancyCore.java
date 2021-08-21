package com.daxton.fancycore;

import com.daxton.fancycore.command.MainCommand;
import com.daxton.fancycore.command.TabCommand;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.listener.attack.AttackListener;
import com.daxton.fancycore.listener.AttackedListener;
import com.daxton.fancycore.listener.InventoryListener;
import com.daxton.fancycore.listener.PlayerListener;
import com.daxton.fancycore.task.server.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.daxton.fancycore.config.FileConfig.languageConfig;

public class FancyCore extends JavaPlugin{

    public static FancyCore fancyCore;

    @Override
    public void onEnable() {
        fancyCore = this;
        //建立設定檔
        FileConfig.execute();
        //前置插件
        if(!DependPlugins.depend()){
            fancyCore.setEnabled(false);
           // fancyCore.onDisable();
            return;
        }
        //指令
        Objects.requireNonNull(Bukkit.getPluginCommand("fancycore")).setExecutor(new MainCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fancycore")).setTabCompleter(new TabCommand());
        //玩家監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new AttackListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new AttackedListener(), fancyCore);
        //只在開服時執行的任務
        Start.execute();
    }

    @Override
    public void onDisable() {
        fancyCore.getLogger().info(languageConfig.getString("LogMessage.Disable"));
    }

}
