package com.daxton.fancycore.api.aims.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.many.Line;
import com.daxton.fancycore.api.aims.entity.many.Radius;
import com.daxton.fancycore.api.aims.entity.one.LookTarget;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetEntity {

    public static List<LivingEntity> get(LivingEntity self, LivingEntity target, Map<String, String> targetMap){
        List<LivingEntity> targetList = new ArrayList<>();
        MapGetKey targetMapHandle = new MapGetKey(targetMap);

        //瞄準目標
        String targetKey = targetMapHandle.getString(new String[]{"targettype"},"null");

        //半徑
        double radius = targetMapHandle.getDouble(new String[]{"radius","r"},1);

        //距離
        double distance = targetMapHandle.getDouble(new String[]{"distance","d"},0);

        //篩選目標
        String filters = targetMapHandle.getString(new String[]{"filters","f"},"null");

        if(target == null && distance > 0){
            target = LookTarget.getLivingTarget(self,distance);
        }
        FancyCore fancyCore = FancyCore.fancyCore;
        switch (targetKey){
            case "selfradius":
                List<LivingEntity> livingEntityList = Radius.getSelfRadiusLivingEntities(self,radius, false);
                targetList.addAll(livingEntityList);
                break;
            case "targetradius":
                if(target != null){
                    List<LivingEntity> livingEntityList2 = Radius.getTargetRadiusLivingEntities(self,target,radius, false, true);
                    targetList.addAll(livingEntityList2);
                }
                break;
            case "selfline":
                List<LivingEntity> livingEntityList3 = Line.getMulti(self,radius,0.2, false);
                targetList.addAll(livingEntityList3);
                break;
            case "target":
                if(target != null){
                    targetList.add(target);
                }
                break;
            case "self":
                targetList.add(self);
                break;
            case "server":
                fancyCore.getServer().getWorlds().forEach(world -> world.getEntities().forEach(entity -> {
                    if(entity instanceof LivingEntity livingEntity){
                        targetList.add(livingEntity);
                    }
                }));
            case "selfinworld":
                self.getWorld().getEntities().forEach(entity -> {
                    if(entity instanceof LivingEntity livingEntity){
                        targetList.add(livingEntity);
                    }
                });
                break;

        }

        return targetList;
    }

}
