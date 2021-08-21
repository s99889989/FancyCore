package com.daxton.fancycore.gui;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.config.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainOpen {

	public static Map<UUID ,GUI> player_Gui_Map = new HashMap<>();



	public static void open(Player player){
//		UUID uuid = player.getUniqueId();
//		if(player_Gui_Map.get(uuid) == null){
//			FileConfiguration config = FileConfig.config_Map.get("config.yml");
//			String s = config.getString("Title");
//			int size = config.getInt("Size");
//			GUI gui = GUI.createGui(player, size, s);
//			gui.open(gui);
//		}else {
//			GUI gui = player_Gui_Map.get(uuid);
//			gui.open(gui);
//		}

	}


}
