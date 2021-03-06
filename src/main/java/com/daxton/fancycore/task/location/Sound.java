package com.daxton.fancycore.task.location;

import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public class Sound implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //聲音名稱
        String sound = actionMapHandle.getString(new String[]{"sound","s"},"");
        //音量
        float volume = actionMapHandle.getFloat(new String[]{"volume","v"},1);
        //音調
        float pitch = actionMapHandle.getFloat(new String[]{"pitch","p"},1);
        //聲音的分類
        SoundCategory category = actionMapHandle.getSoundCategory(new String[]{"category","c"},"PLAYERS");
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);

        if(location != null){
            //發出聲音
            location.getWorld().playSound(location, sound, category, volume, pitch);
        }

    }

}
