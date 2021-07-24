package com.daxton.fancycore.task;

import com.daxton.fancycore.api.taskaction.MapGetKey;
import com.daxton.fancycore.task.location.Sound;
import com.daxton.fancycore.task.player.Title;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public class TaskAction {

    public static void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, String taskID, Location inputLocation){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        String judgMent = actionMapHandle.getString(new String[]{"ActionType"}, "");

        if(judgMent.equalsIgnoreCase("sound")){
            Sound.execute(self, target, action_Map, taskID, inputLocation);
            return;
        }
        if(judgMent.equalsIgnoreCase("title")){
            Title.execute(self, target, action_Map, taskID);
        }

    }

}
