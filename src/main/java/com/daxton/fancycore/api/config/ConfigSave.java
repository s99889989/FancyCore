package com.daxton.fancycore.api.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigSave {

	public static void filter(Plugin plugin, Map<String, FileConfiguration> config_Map, String startKey){
		config_Map.forEach((s, configuration) -> {
			if(s.startsWith(startKey)){
				File file = new File(plugin.getDataFolder(), s);
				try {
					configuration.save(file);
				}catch (IOException exception){
					exception.printStackTrace();
				}
			}
		});
	}

}
