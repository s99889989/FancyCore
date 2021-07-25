package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public class Level implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
    }

}
