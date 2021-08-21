package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Title implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //標題淡入時間,以tick為單位.
        int fadeIn = actionMapHandle.getInt(new String[]{"fadein","fi"},10);
        //標題停留/展示時長,以tick為單位.
        int duration = actionMapHandle.getInt(new String[]{"duration","d"},70);
        // 標題淡出時間,以tick為單位.
        int fadeOut = actionMapHandle.getInt(new String[]{"fadeout","fo"},70);
        //標題內容
        String title = actionMapHandle.getString(new String[]{"title","t"},"");
        //副標題內容
        String subTitle = actionMapHandle.getString(new String[]{"subtitle","s"},"");

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
        //
        livingEntityList.forEach(livingEntity -> {
            if(livingEntity instanceof Player){
                Player player = (Player) livingEntity;
                //發送Title
                player.sendTitle(title, subTitle, fadeIn, duration, fadeOut);
            }
        });

    }


}
