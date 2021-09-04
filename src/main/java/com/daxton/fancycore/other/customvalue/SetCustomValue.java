package com.daxton.fancycore.other.customvalue;

import com.daxton.fancycore.api.config.SearchConfigMap;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.OtherManager;
import org.bukkit.configuration.file.FileConfiguration;

public class SetCustomValue {

	public static void execute(){
		SearchConfigMap.fileNameList(FileConfig.config_Map, "custom-value/", false).forEach(s -> {
			FileConfiguration config = FileConfig.config_Map.get("custom-value/"+s);
			config.getConfigurationSection("").getKeys(false).forEach(key->{
				String value = config.getString(key+".name");
				String base = config.getString(key+".base");
				key = key.toLowerCase();
				if(base == null){
					base = "0";
				}
				OtherManager.custom_Value_Default.put(key, base);
				if(value == null){
					value = "";
				}
				OtherManager.custom_Value.put(key, value);

			});
		});
	}

}
