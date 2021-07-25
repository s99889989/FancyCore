package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.entity.BukkitAttributeSet;
import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import com.daxton.fancycore.api.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class AttributeSet implements FancyAction {
    //設置目標屬性
    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //屬性名稱
        String attributes = actionMapHandle.getString(new String[]{"attributes","attr"},"GENERIC_MAX_HEALTH");

        //屬性標籤
        String label = actionMapHandle.getString(new String[]{"label"},"");

        //持續時間
        int duration = actionMapHandle.getInt(new String[]{"duration","dt"},200);

        //量
        double amount = actionMapHandle.getDouble(new String[]{"amount","a"},1);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
        //
        livingEntityList.forEach(livingEntity -> setAttributes(livingEntity, attributes, amount , label, duration));
    }

    public void setAttributes(LivingEntity livingEntity, String attributes, Double amount, String label, int duration){

        BukkitAttributeSet.removeAddAttribute(livingEntity, attributes, "ADD_NUMBER", amount, label);

        new BukkitRunnable() {
            @Override
            public void run() {
                BukkitAttributeSet.removeAttribute(livingEntity, attributes, label);
            }
        }.runTaskLater(FancyCore.fancyCore, duration);


    }

}
