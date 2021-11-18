package com.daxton.fancycore;

import com.daxton.fancycore.api.fancyclient.ClientConnect;
import com.daxton.fancycore.command.MainCommand;
import com.daxton.fancycore.command.TabCommand;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.listener.*;
import com.daxton.fancycore.server.Start;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.daxton.fancycore.config.FileConfig.languageConfig;

public class FancyCore extends JavaPlugin implements Listener {

    public static FancyCore fancyCore;

    private static final int IDX = 233;
    private final String channel = "msgtutor:test";

    @Override
    public void onEnable() {
        fancyCore = this;
        //建立設定檔
        FileConfig.execute();
        //前置插件
        if(!DependPlugins.depend()){
            fancyCore.setEnabled(false);
            return;
        }
        //只在開服時執行的任務
        Start.execute();
        //指令
        Objects.requireNonNull(Bukkit.getPluginCommand("fancycore")).setExecutor(new MainCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fancycore")).setTabCompleter(new TabCommand());
        //玩家監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new AttackedListener(), fancyCore);
        Bukkit.getPluginManager().registerEvents(new MobListener(), fancyCore);
        //客戶端模組監聽
        Bukkit.getPluginManager().registerEvents(new ModListener(), fancyCore);
        //傷害核心
        AttackCore.setCore();
        //連接頻道
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(fancyCore, ClientConnect.channel, new ClientListener());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(fancyCore, ClientConnect.channel);
    }

    public static void sendLogger(String logger){
        fancyCore.getLogger().info(logger);
    }

    @Override
    public void onDisable() {
        fancyCore.getLogger().info(languageConfig.getString("LogMessage.Disable"));
    }

}
