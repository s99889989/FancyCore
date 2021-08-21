package com.daxton.fancycore.api.character.placeholder;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class PlaceholderAttributes {

    public PlaceholderAttributes(){

    }

    public static String valueOf(LivingEntity entity, String inputString){
        String outputString = "0";

        try {
            if(inputString.toLowerCase().contains("max_health")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_MAX_HEALTH")).getValue());
            }
            if(inputString.toLowerCase().contains("follow_range")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_FOLLOW_RANGE")).getValue());
            }
            if(inputString.toLowerCase().contains("knockback_resistance")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_KNOCKBACK_RESISTANCE")).getValue());
            }
            if(inputString.toLowerCase().contains("movement_speed")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_MOVEMENT_SPEED")).getValue());
            }
            if(inputString.toLowerCase().contains("flying_speed")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_FLYING_SPEED")).getValue());
            }
            if(inputString.toLowerCase().contains("attack_damage")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_ATTACK_DAMAGE")).getValue());
            }
            if(inputString.toLowerCase().contains("attack_knockback")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_ATTACK_KNOCKBACK")).getValue());
            }
            if(inputString.toLowerCase().contains("attack_speed")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_ATTACK_SPEED")).getValue());
            }
            if(inputString.toLowerCase().contains("armor")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_ARMOR")).getValue());
            }
            if(inputString.toLowerCase().contains("armor_toughness")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_ARMOR_TOUGHNESS")).getValue());
            }
            if(inputString.toLowerCase().contains("luck")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"GENERIC_LUCK")).getValue());
            }
            if(inputString.toLowerCase().contains("horse_jump_strength")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"HORSE_JUMP_STRENGTH")).getValue());
            }
            if(inputString.toLowerCase().contains("zombie_spawn_reinforcements")){
                outputString = String.valueOf(entity.getAttribute(Enum.valueOf(Attribute.class,"ZOMBIE_SPAWN_REINFORCEMENTS")).getValue());
            }
        }catch (NullPointerException exception){
            //
        }

        return outputString;
    }

}
