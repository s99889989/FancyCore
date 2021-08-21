package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class LoggerMessage implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        livingEntityList.forEach(livingEntity -> {
            MapGetKey actionMapHandle2 = new MapGetKey(action_Map, self, target);

            String message = actionMapHandle2.getString(new String[]{"message","m"},"");

            FancyCore.fancyCore.getLogger().info(message);
        });

    }

}
