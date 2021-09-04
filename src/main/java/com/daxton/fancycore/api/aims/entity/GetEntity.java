package com.daxton.fancycore.api.aims.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.many.Line;
import com.daxton.fancycore.api.aims.entity.many.Radius;
import com.daxton.fancycore.api.aims.entity.one.LookTarget;
import com.daxton.fancycore.other.taskaction.MapGetKey;
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
        FancyCore fancyCore = FancyCore.fancyCore;
        if(target == null && distance > 0){
            target = LookTarget.getLivingTarget(self,distance);
        }
        switch (targetKey.toLowerCase()){
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
            case "self":
                targetList.add(self);
                break;
            case "server":
                fancyCore.getServer().getWorlds().forEach(world -> world.getEntities().forEach(entity -> {
                    if(entity instanceof LivingEntity){
                        LivingEntity livingEntity = (LivingEntity) entity;
                        targetList.add(livingEntity);
                    }
                }));
            case "selfinworld":
                self.getWorld().getEntities().forEach(entity -> {
                    if(entity instanceof LivingEntity){
                        LivingEntity livingEntity = (LivingEntity) entity;
                        targetList.add(livingEntity);
                    }
                });
                break;
            case "target":
            default:
                if(target != null){
                    targetList.add(target);
                }
        }

        //篩選目標
        List<LivingEntity> targetFiltList = new ArrayList<>();

        if(!filters.equals("null")){
            for(LivingEntity livingEntity : targetList){
                if(Filter.valueOf(livingEntity, filters)){
                    targetFiltList.add(livingEntity);
                }
            }
        }else {
            targetFiltList = targetList;
        }


        return targetFiltList;
    }

}
