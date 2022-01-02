package com.daxton.fancycore.api.character.placeholder;

import com.daxton.fancyclasses.api.placeholder.ClassPlaceholder;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;

import com.daxton.fancymobs.api.placeholder.BasePlaceholder;
import com.daxton.fancymobs.api.placeholder.FancyMobsPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlaceholderTarget {


    public PlaceholderTarget(){

    }

    public static String valueOf(LivingEntity entity, String inputString){

        String key = inputString.replace("_target","").replace(">","");

        if(entity instanceof Player){
            if(key.toLowerCase().contains("<fc_base_")){
                return PlaceholderBase.valueOf(entity,null,key);
            }
            if(key.toLowerCase().contains("<fc_player_")){
                return PlaceholderPlayer.valueOf(entity,key);
            }
            if(key.toLowerCase().contains("<fc_attribute_")){
                return PlaceholderAttributes.valueOf(entity, key);
            }
            if(key.toLowerCase().contains("<fc_class_") && Bukkit.getPluginManager().isPluginEnabled("FancyClasses")){
                return ClassPlaceholder.valueOf(entity, key);
            }
            if(key.toLowerCase().contains("<fc_gui_")){
                UUID uuid = entity.getUniqueId();
                PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                if(playerDataFancy != null){
                    playerDataFancy.getGuiValue(key);
                }
            }
        }else {

            if(key.toLowerCase().contains("<fc_base_value_") && Bukkit.getPluginManager().isPluginEnabled("FancyMobs")){
                return BasePlaceholder.valueOf(entity,key);
            }
            if(key.toLowerCase().contains("<fc_base_")){
                return PlaceholderBase.valueOf(entity, null, key);
            }
            if(key.toLowerCase().contains("<fc_mythic_") && Bukkit.getServer().getPluginManager().getPlugin("FancyMobs") != null && Bukkit.getPluginManager().isPluginEnabled("FancyMobs")){
                return FancyMobsPlaceholder.valueOf(entity,key);
            }
            if(key.toLowerCase().contains("<fc_attribute_")){
                return PlaceholderAttributes.valueOf(entity, key);
            }

        }
        return "0";
    }

}
