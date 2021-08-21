package com.daxton.fancycore.api.character.placeholder;

import com.daxton.fancymobs.api.placeholder.FancyMobsPlaceholder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PlaceholderSelf {

    public PlaceholderSelf(){

    }

    public static String valueOf(LivingEntity entity,LivingEntity target, String inputString){
        String outputString = "0";
        String key = inputString.replace("_self","").replace(">","");

        if(entity instanceof Player){
            if(key.toLowerCase().contains("<fc_base_")){
                outputString = PlaceholderBase.valueOf(entity,target,key);
            }
            if(key.toLowerCase().contains("<fc_player_")){
                outputString = PlaceholderPlayer.valueOf(entity,key);
            }
            if(key.toLowerCase().contains("<fc_attribute_")){
                outputString = PlaceholderAttributes.valueOf(entity, key);
            }
        }else {
            if(key.toLowerCase().contains("<fc_base_")){
                outputString = PlaceholderBase.valueOf(entity,target,key);
            }
            if(key.toLowerCase().contains("<fc_mythic_")){
                outputString = FancyMobsPlaceholder.valueOf(entity,key);
            }
            if(key.toLowerCase().contains("<cd_attribute_")){
                outputString = PlaceholderAttributes.valueOf(entity, key);
            }
        }
        return outputString;
    }

}
