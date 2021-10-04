package com.daxton.fancycore.task.player;

import com.daxton.fancyclasses.api.dataplayer.PlayerClassData;
import com.daxton.fancyclasses.manager.ClassesManager;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Mana implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        if(Bukkit.getServer().getPluginManager().getPlugin("FancyClasses") == null || !Bukkit.getPluginManager().isPluginEnabled("FancyClasses")){
            return;
        }
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //補量
        double amount = actionMapHandle.getDouble(new String[]{"amount","a"},10);
        //FancyCore.fancyCore.getLogger().info("回復量: "+amount);
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "@Self");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        //
        livingEntityList.forEach(livingEntity -> giveHeal(livingEntity, amount));
    }

    public static void giveHeal(LivingEntity livingEntity, double amount){
        UUID uuid = livingEntity.getUniqueId();
        PlayerClassData playerClassData = ClassesManager.player_ClassData_Map.get(uuid);
        if(playerClassData != null){
            if(playerClassData.nowMana >= playerClassData.maxMana){
                return;
            }

            double giveMana = playerClassData.nowMana+amount;
            playerClassData.nowMana = Math.min(giveMana, playerClassData.maxMana);

        }

    }

}
