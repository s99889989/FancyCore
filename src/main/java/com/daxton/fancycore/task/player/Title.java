package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.taskaction.MapGetKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Map;

public class Title {

    public static void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        int fadeIn = actionMapHandle.getInt(new String[]{"fadein","fi"},10);

        int duration = actionMapHandle.getInt(new String[]{"duration","d"},70);

        int fadeOut = actionMapHandle.getInt(new String[]{"fadeout","fo"},70);

//        List<LivingEntity> livingEntityList = actionMapHandle.getLivingEntityListSelf();
//
//        livingEntityList.forEach(livingEntity -> {
//
//            if(livingEntity instanceof Player){
//                Player player = (Player) livingEntity;
//                MapGetKey actionMapHandle2 = new  MapGetKey(action_Map, self, livingEntity);
//                String title = actionMapHandle2.getString(new String[]{"title","t"},"");
//
//                String subTitle = actionMapHandle2.getString(new String[]{"subtitle","s"},"");
//                sendTitle(player, title, subTitle, fadeIn, duration, fadeOut);
//
//            }
//
//        });

        String title = actionMapHandle.getString(new String[]{"title","t"},"");

        String subTitle = actionMapHandle.getString(new String[]{"subtitle","s"},"");
        if(self instanceof Player player){
            sendTitle(player, title, subTitle, fadeIn, duration, fadeOut);
        }


    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int duration, int fadeOut){
        player.sendTitle(title,subTitle,fadeIn,duration,fadeOut);
    }

}
