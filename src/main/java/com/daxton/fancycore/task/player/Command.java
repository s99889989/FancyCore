package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class Command implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        boolean consolesB = actionMapHandle.getBoolean(new String[]{"c","consoles"},false);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> targetList = GetEntity.get(self, target, targetMap);

        targetList.forEach(livingEntity -> {
            if(livingEntity instanceof Player){

                Player player = (Player) livingEntity;

                MapGetKey actionMapHandle2 = new MapGetKey(action_Map, self, player);

                //獲得內容
                String message = actionMapHandle2.getString(new String[]{"m","message"},"");
                if(consolesB){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
                }else {
                    player.performCommand(message);
                }

            }
        });

    }

}
