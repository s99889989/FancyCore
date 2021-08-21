package com.daxton.fancycore.api.aims.entity;

import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancymobs.api.FancyMob;
import com.daxton.fancymobs.manager.MobManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

public class Filter {
	//過濾目標
	public static boolean valueOf(LivingEntity livingEntity, String filteName){
		if(filteName.equals("null")){
			return true;
		}

		boolean b = true;

		FileConfiguration TargetFiltersConfig = FileConfig.config_Map.get("Other/TargetFilters.yml");
		List<String> filteList = TargetFiltersConfig.getStringList(filteName+".TargetFilters");

		for(String filteKey : filteList){

			//篩選掉派系目標
			if(filteKey.toLowerCase().contains("factions")){
				b = isFactions(livingEntity, filteKey);
			}
			//篩選生物類別目標
			if(filteKey.toLowerCase().contains("entitytypelist")){
				b = isEntityType(livingEntity, filteKey);
			}

			//篩選生物MM類別目標
			if(filteKey.toLowerCase().contains("mythictypelist")){
				b = isMythicType(livingEntity, filteKey);
			}

		}

		return b;
	}

	//MM生物ID
	public static boolean isMythicType(LivingEntity livingEntity, String filteKey){
		boolean b = true;
		if (Bukkit.getServer().getPluginManager().getPlugin("FancyMobs") != null){
			String[] filteKey1 = filteKey.split("=");
			if(filteKey1.length == 3){

				FileConfiguration mythicTypeListConfig = FileConfig.config_Map.get("Other/MythicTypeList.yml");
				List<String> mythicTypeList = mythicTypeListConfig.getStringList(filteKey1[2]+".mythicTypeList");
				UUID uuid = livingEntity.getUniqueId();
				FancyMob fancyMob = MobManager.fancy_Mob_Map.get(uuid);
				String id = fancyMob.getMythicID();
				if(filteKey1[0].equalsIgnoreCase("remove")){
					b = !mythicTypeList.contains(id);
				}
				if(filteKey1[0].equalsIgnoreCase("add")){
					b = mythicTypeList.contains(id);
				}

			}
		}
		return b;
	}

	//生物類別判定
	public static boolean isEntityType(LivingEntity livingEntity, String filteKey){
		boolean b = true;
		String[] filteKey1 = filteKey.split("=");
		if(filteKey1.length == 3){
			FileConfiguration entityTypeListConfig = FileConfig.config_Map.get("Other/EntityTypeList.yml");
			List<String> entityTypeList = entityTypeListConfig.getStringList(filteKey1[2]+".entityTypeList");
			if(filteKey1[0].equalsIgnoreCase("remove")){
				b = !entityTypeList.contains(livingEntity.getType().toString());
			}
			if(filteKey1[0].equalsIgnoreCase("add")){
				b = entityTypeList.contains(livingEntity.getType().toString());
			}

		}
		return b;
	}

	//派系判定
	public static boolean isFactions(LivingEntity livingEntity, String filteKey){
		boolean b = true;
		if (Bukkit.getServer().getPluginManager().getPlugin("FancyMobs") != null){
			String[] filteKey1 = filteKey.split("=");
			if(filteKey1.length == 3){
				UUID uuid = livingEntity.getUniqueId();
				FancyMob fancyMob = MobManager.fancy_Mob_Map.get(uuid);
				String faction = fancyMob.getFaction();
				if(filteKey1[0].equalsIgnoreCase("remove")){
					b = !filteKey1[2].equals(faction);
				}
				if(filteKey1[0].equalsIgnoreCase("add")){
					b = filteKey1[2].equals(faction);
				}

			}
		}
		return b;
	}

}
