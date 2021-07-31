package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import com.daxton.fancycore.api.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class Heal implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //補量
        double amount = actionMapHandle.getDouble(new String[]{"amount","a"},10);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        //
        livingEntityList.forEach(livingEntity -> giveHeal(livingEntity, amount));
    }

    public static void giveHeal(LivingEntity livingEntity,double amount){

        double giveHealth = livingEntity.getHealth()+amount;
        double maxHealth = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if(giveHealth > maxHealth){
            giveHealth = giveHealth - (giveHealth - maxHealth);
        }

        livingEntity.setHealth(giveHealth);
    }

}
