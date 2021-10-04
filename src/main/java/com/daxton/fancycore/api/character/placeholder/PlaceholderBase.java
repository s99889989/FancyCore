package com.daxton.fancycore.api.character.placeholder;


import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class PlaceholderBase {


    //基礎
    public PlaceholderBase(){

    }

    public static String valueOf(LivingEntity entity,LivingEntity target, String inputString){



        //名稱
        if(inputString.toLowerCase().contains("<fc_base_name")){
            return entity.getName();
        }
        //UUID
        if(inputString.toLowerCase().contains("<fc_base_uuid")){
            return entity.getUniqueId().toString();
        }
        //身高
        if(inputString.toLowerCase().contains("<fc_base_height")){
            return String.valueOf(entity.getHeight());
        }
        //目前血量
        if(inputString.toLowerCase().contains("<fc_base_nowhealth")){
            return String.valueOf(entity.getHealth());
        }
        //最高血量
        if(inputString.toLowerCase().contains("<fc_base_maxhealth")){
            return String.valueOf(entity.getAttribute(GENERIC_MAX_HEALTH).getValue());
        }
        //實體類型
        if(inputString.toLowerCase().contains("<fc_base_type")){
            return entity.getType().toString();
        }
        //生態域
        if(inputString.toLowerCase().contains("<fc_base_biome")){
            return entity.getLocation().getBlock().getBiome().toString();
        }
        //世界
        if(inputString.toLowerCase().contains("<fc_base_world")){
            return entity.getWorld().toString();
        }
        //座標X
        if(inputString.toLowerCase().contains("<fc_base_loc_x")){
            return String.valueOf(entity.getLocation().getX());
        }
        //座標Y
        if(inputString.toLowerCase().contains("<fc_base_loc_y")){
            return String.valueOf(entity.getLocation().getY());
        }
        //座標Z
        if(inputString.toLowerCase().contains("<fc_base_loc_z")){
            return String.valueOf(entity.getLocation().getZ());
        }
        //向量X
        if(inputString.toLowerCase().contains("<fc_base_vec_x")){
            return String.valueOf(entity.getLocation().getDirection().getX());
        }
        //向量Y
        if(inputString.toLowerCase().contains("<fc_base_vec_y")){
            return String.valueOf(entity.getLocation().getDirection().getY());
        }
        //向量Z
        if(inputString.toLowerCase().contains("<fc_base_vec_z")){
            return String.valueOf(entity.getLocation().getDirection().getZ());
        }
        UUID uuid = entity.getUniqueId();
        PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
        if(playerDataFancy != null){
            //自訂值
            if(inputString.toLowerCase().contains("<fc_base_value_")){
                String key = inputString.replace("<fc_base_value_", "").toLowerCase();
                return playerDataFancy.getCustomValue(key);
            }
            //攻擊傷害
            if(inputString.toLowerCase().contains("<fc_base_attack_number")){
                if(target != null){
                    String targetUUIDString = target.getUniqueId().toString();
                    if(playerDataFancy.attack_number2.containsKey(targetUUIDString)){
                        return playerDataFancy.attack_number2.get(targetUUIDString);
                    }else {
                        return playerDataFancy.attack_number;
                    }
                }else {
                    return playerDataFancy.attack_number;
                }
            }
            //被攻擊傷害
            if(inputString.toLowerCase().contains("<fc_base_damaged_number")){
                return String.valueOf(playerDataFancy.attacked_number);
            }
        }


        return "";
    }


}
