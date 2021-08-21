package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.PackPlayer;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.manager.PlayerManagerCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.comphenix.protocol.wrappers.EnumWrappers.TitleAction.ACTIONBAR;

public class ActionBar implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //是否要移除
        boolean remove = actionMapHandle.getBoolean(new String[]{"remove","r"}, false);
        //訊息
        String message = actionMapHandle.getString(new String[]{"message","m"},"");
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
        //
        livingEntityList.forEach(livingEntity -> {
            if(livingEntity instanceof Player){
                Player player = (Player) livingEntity;
                UUID uuid = player.getUniqueId();
                //是否移除其他ActionBar
                PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                playerDataFancy.actionBar_remove = remove;

                sendActionBar(player,message);
            }
        });

    }

    public static void sendActionBar(Player player, String message){
        try {
            PackPlayer.sendActionBar(player, message, ACTIONBAR, 1, 1, 1);
        }catch (IllegalArgumentException exception){
            try {
                player.sendActionBar(message);
            }catch (NoSuchMethodError exception2){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            }

        }

    }

}
