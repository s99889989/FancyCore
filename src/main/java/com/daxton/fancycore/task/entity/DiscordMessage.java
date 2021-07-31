package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import com.daxton.fancycore.api.taskaction.StringToMap;
import com.daxton.fancycore.manager.OtherManager;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.channel.TextChannel;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class DiscordMessage implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        long channelID = actionMapHandle.getLong(new String[]{"channel","c"},1L);

        String message = actionMapHandle.getString(new String[]{"message","m"},"");

        //目標
//        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
//        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
//        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        //
        sendDCMessage(message, channelID);
    }
    public static void sendDCMessage(String message,long channelID){

        TextChannel channel = (TextChannel) OtherManager.client
                .getChannelById(Snowflake.of(channelID))
                .block();
        channel.createMessage(message).block();
    }
}
