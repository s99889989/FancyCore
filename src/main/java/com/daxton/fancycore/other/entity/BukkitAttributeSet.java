package com.daxton.fancycore.other.entity;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;

public class BukkitAttributeSet {

    //設置實體屬性//

    //移除舊有屬性再增加。(生命實體、屬性名稱、屬性增加類型、屬性增加數量、屬性標籤)
    public static void removeAddAttribute(LivingEntity livingEntity, String inherit, String operation, double addNumber, String label){
        removeAttribute(livingEntity, inherit, label);
        addAttribute(livingEntity, inherit, operation, addNumber, label);
    }

    //增加原有屬性。(生命實體、屬性名稱、屬性增加類型、屬性增加數量、屬性標籤)
    public static void addAttribute(LivingEntity livingEntity, String inherit, String operation, double addNumber, String attributeName){
        try {
            AttributeInstance attributeInstance = livingEntity.getAttribute(Enum.valueOf(Attribute.class, inherit.toUpperCase()));
            AttributeModifier healthModifier = new AttributeModifier("fancycore."+attributeName, addNumber, Enum.valueOf(AttributeModifier.Operation.class, operation.toUpperCase()));
            attributeInstance.addModifier(healthModifier);
        }catch (IllegalArgumentException exception){

        }
//        AttributeInstance attributeInstance = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
//        AttributeModifier healthModifier = new AttributeModifier("abc123456", 10, AttributeModifier.Operation.ADD_NUMBER);
//        attributeInstance.addModifier(healthModifier);

    }
    //清除指定標籤屬性。(生命實體、屬性名稱、屬性標籤)
    public static void removeAttribute(LivingEntity player, String inherit, String label){
        try {
            AttributeInstance attributeInstance = player.getAttribute(Enum.valueOf(Attribute.class,inherit));
            if(attributeInstance != null){
                for(AttributeModifier attributeModifier : attributeInstance.getModifiers()){
                    if(attributeModifier.toString().contains("fancycore."+label)){
                        //player.sendMessage(attributeModifier.getName()+" : "+attributeModifier.getAmount());
                        attributeInstance.removeModifier(attributeModifier);
                    }
                }
            }
        }catch (IllegalArgumentException exception){
            //
        }

    }

    //移除該標籤屬性
    public static void removeLabelAttr(LivingEntity livingEntity, String label){
        removeAttribute(livingEntity, "GENERIC_MAX_HEALTH", label);
        removeAttribute(livingEntity, "GENERIC_KNOCKBACK_RESISTANCE", label);
        removeAttribute(livingEntity, "GENERIC_MOVEMENT_SPEED", label);
        removeAttribute(livingEntity, "GENERIC_ATTACK_DAMAGE", label);
        removeAttribute(livingEntity, "GENERIC_ATTACK_SPEED", label);
        removeAttribute(livingEntity, "GENERIC_ARMOR", label);
        removeAttribute(livingEntity, "GENERIC_ARMOR_TOUGHNESS", label);
        removeAttribute(livingEntity, "GENERIC_LUCK", label);
    }

    //清除該項屬性。(生命實體、屬性名稱)
    public static void removeTypeAttribute(LivingEntity livingEntity, String inherit){
        try {
            AttributeInstance attributeInstance = livingEntity.getAttribute(Enum.valueOf(Attribute.class, inherit));
            if(attributeInstance != null){
                for(AttributeModifier attributeModifier : attributeInstance.getModifiers()){
                    String attrName = attributeModifier.getName();
                    //CustomDisplay.getCustomDisplay().getLogger().info("包含: "+attrName);
                    if(attrName.contains("fancycore.")){
                        //player.sendMessage(attributeModifier.getName()+" : "+attributeModifier.getAmount());
                        attributeInstance.removeModifier(attributeModifier);
                    }
                }
            }
        }catch (IllegalArgumentException exception){
            //
        }
    }
    //移除玩家身上，關於此插件有關的全部屬性
    public static void removeAllAttribute(LivingEntity livingEntity){
        removeTypeAttribute(livingEntity,"GENERIC_MAX_HEALTH");
        //removeAttribute(player,"GENERIC_FOLLOW_RANGE");
        removeTypeAttribute(livingEntity,"GENERIC_KNOCKBACK_RESISTANCE");
        removeTypeAttribute(livingEntity,"GENERIC_MOVEMENT_SPEED");
        //removeAttribute(player,"GENERIC_FLYING_SPEED");
        removeTypeAttribute(livingEntity,"GENERIC_ATTACK_DAMAGE");
        //removeAttribute(player,"GENERIC_ATTACK_KNOCKBACK");
        removeTypeAttribute(livingEntity,"GENERIC_ATTACK_SPEED");
        removeTypeAttribute(livingEntity,"GENERIC_ARMOR");
        removeTypeAttribute(livingEntity,"GENERIC_ARMOR_TOUGHNESS");
        removeTypeAttribute(livingEntity,"GENERIC_LUCK");
        //removeAttribute(player,"HORSE_JUMP_STRENGTH");
        //removeAttribute(player,"ZOMBIE_SPAWN_REINFORCEMENTS");
    }

}
