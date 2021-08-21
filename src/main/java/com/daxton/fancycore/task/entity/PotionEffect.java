package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;

public class PotionEffect implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //藥水效果
        PotionEffectType potion = actionMapHandle.getPotionEffectType(new String[]{"potion"},PotionEffectType.INCREASE_DAMAGE);
        //持續時間
        int duration = actionMapHandle.getInt(new String[]{"duration","dt"},200);
        //藥水等級
        int amplifir = actionMapHandle.getInt(new String[]{"amplifir","ap"},1);

        boolean ambient = actionMapHandle.getBoolean(new String[]{"ambient","ab"},false);
        //藥水效果
        boolean particles = actionMapHandle.getBoolean(new String[]{"particles","p"},false);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        livingEntityList.forEach(livingEntity -> {
            livingEntity.addPotionEffect(new org.bukkit.potion.PotionEffect(potion, duration, amplifir, ambient, particles));
        });

    }

}
