package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.number.Count;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class Damage implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        int amount = actionMapHandle.getInt(new String[]{"amount","a"}, 1);

        String type = actionMapHandle.getString(new String[]{"type","t"},"melee");

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
        //
        //FancyCore.fancyCore.getLogger().info("技能: "+type);
        if(type.equalsIgnoreCase("range_multiply")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.3333)));
            return;
        }
        if(type.equalsIgnoreCase("range_add")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.3222)));
            return;
        }
        if(type.equalsIgnoreCase("range")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.3111)));
            return;
        }

        if(type.equalsIgnoreCase("magic_multiply")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.2333)));
            return;
        }
        if(type.equalsIgnoreCase("magic_add")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.2222)));
            return;
        }
        if(type.equalsIgnoreCase("magic")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.2111)));
            return;
        }
        if(type.equalsIgnoreCase("melee_multiply")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.1333)));
            return;
        }
        if(type.equalsIgnoreCase("melee_add")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.1222)));
            return;
        }
        if(type.equalsIgnoreCase("melee")){
            livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.1111)));
            return;
        }
        livingEntityList.forEach(livingEntity ->  setDamage(self, livingEntity, Count.add(amount, 0.1111)));
    }
    public static void setDamage(LivingEntity self, LivingEntity target,double amount){
        target.damage(amount, self);
    }
}
