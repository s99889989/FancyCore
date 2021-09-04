package com.daxton.fancycore;

import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.listener.attack.AttackListener;
import com.daxton.fancycore.listener.attack.customcore.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class AttackCore {

	public static void setCore(){
		FancyCore fancyCore = FancyCore.fancyCore;

		FileConfiguration config = FileConfig.config_Map.get("config.yml");

		String attackCore = config.getString("AttackCore")+"";

		if(attackCore.equalsIgnoreCase("FancyCore")){
			Bukkit.getPluginManager().registerEvents(new MainAttack(), fancyCore);
			Bukkit.getPluginManager().registerEvents(new MagicAttack(), fancyCore);
			Bukkit.getPluginManager().registerEvents(new MeleeAttack(), fancyCore);
			Bukkit.getPluginManager().registerEvents(new RangeAttack(), fancyCore);
			Bukkit.getPluginManager().registerEvents(new NumberAttack(), fancyCore);
			return;
		}
		Bukkit.getPluginManager().registerEvents(new AttackListener(), fancyCore);


	}

}
