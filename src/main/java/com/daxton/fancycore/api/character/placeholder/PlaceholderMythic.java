package com.daxton.fancycore.api.character.placeholder;


import com.daxton.fancycore.api.hook.MythicMob.MobData;
import com.daxton.fancycore.manager.MobManager;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class PlaceholderMythic {


    public PlaceholderMythic(){

    }

    public static String valueOf(LivingEntity entity, String inputString){


        String outputString = "0";
        UUID uuid = entity.getUniqueId();
        String uuidString = entity.getUniqueId().toString();

        ActiveMob activeMob = MobManager.mythicMobs_ActiveMob_Map.get(uuid);


        if(MobManager.mythicMobs_ActiveMob_Map.get(uuid) != null){
            String mobID = activeMob.getMobType();
            //獲取MM魔物ID
            if(inputString.toLowerCase().contains("<cd_mythic_id")){
                outputString = mobID;
            }
            //獲取MM魔物等級
            if(inputString.toLowerCase().contains("<cd_mythic_level")){
                outputString = String.valueOf(activeMob.getLevel());
            }
            //獲取MM魔物派系
            if(inputString.toLowerCase().contains("<cd_mythic_faction")){
                outputString =  activeMob.getFaction();
            }

            MobData mobData = MobManager.mob_Data_Map.get(mobID);
            if(MobManager.mob_Data_Map.get(mobID) != null){
                //獲取MM魔物自訂屬性
                if(inputString.toLowerCase().contains("<cd_mythic_class_stats_")){
                    String key = inputString.replace(" ","").replace("<cd_mythic_class_stats_","");
                    outputString =  mobData.getCustomState(key);
                }

            }
        }





        return outputString;
    }


}
