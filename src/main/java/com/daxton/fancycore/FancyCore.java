package com.daxton.fancycore;

import com.comphenix.protocol.ProtocolLibrary;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.listener.PlayerListener;
import com.daxton.fancycore.protocol.ProtocolMap;
import com.daxton.fancycore.task.Reload;
import com.daxton.fancycore.task.Start;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;

public class FancyCore extends JavaPlugin{

    public static FancyCore fancyCore;

    @Override
    public void onEnable() {
        fancyCore = this;
        //前置插件
        if(!DependPlugins.depend()){
            fancyCore.setEnabled(false);
            fancyCore.onDisable();
            return;
        }

        //玩家監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyCore);
        //只在開服時執行的任務
        Start.execute();
        //重新讀取的一些任務
        Reload.execute();
    }

    @Override
    public void onDisable() {
        fancyCore.getLogger().info("FancyCore uninstall");
        fancyCore.getLogger().info("FancyCore 卸載");
    }

    //修改saveResource的存檔位置，因為位置是讀取jar內的，所以要去除resource/**/
    public void saveResource(Plugin plugin, String resourcePath, String savePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            return;
        }

        resourcePath = resourcePath.replace('\\', '/');

        String res = savePath;

        InputStream in = plugin.getResource(resourcePath);

        if (in == null) {
            return;
        }

        File outFile = new File(plugin.getDataFolder(), res);

        int lastIndex = res.lastIndexOf('/');
        File outDir = new File(plugin.getDataFolder(), res.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                File outFileF = new File(plugin.getDataFolder(), res);
                OutputStream out = new FileOutputStream(outFileF);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                super.getLogger().log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            super.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }

}
