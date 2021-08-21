package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.manager.PlayerManagerCore;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CustomInventory implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //獲得功能
        String function = actionMapHandle.getString(new String[]{"function","fc"},"");
        //獲得GUIID
        String GuiID = actionMapHandle.getString(new String[]{"guiid"},"Default");
        //獲得數量
        int amount =  actionMapHandle.getInt(new String[]{"amount","a"},27);
        //獲得內容
        String message = actionMapHandle.getString(new String[]{"message","m"},"");

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> targetList = GetEntity.get(self, target, targetMap);

        targetList.forEach(livingEntity -> {
            if(livingEntity instanceof Player){
                Player player = (Player) livingEntity;
                UUID uuid = player.getUniqueId();
                if(function.toLowerCase().contains("gui")){

                }else if(function.toLowerCase().contains("close")){
                    player.closeInventory();
                }else {
                    PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                    if(playerDataFancy.inventory == null){
                        playerDataFancy.inventory = Bukkit.createInventory(null, amount , message);
                        player.openInventory(playerDataFancy.inventory);
                    }else {
                        player.openInventory(playerDataFancy.inventory);
                    }
                }
            }

        });

    }



}
