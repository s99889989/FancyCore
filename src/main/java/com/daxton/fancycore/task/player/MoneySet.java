package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import com.daxton.fancycore.api.taskaction.StringToMap;
import com.daxton.fancycore.manager.Manager;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class MoneySet implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        if(Manager.econ == null){
            return;
        }
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //數量
        double amount = actionMapHandle.getDouble(new String[]{"amount","a"},10);
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        livingEntityList.forEach(livingEntity -> {
            if(livingEntity instanceof Player){
                Player player = (Player) livingEntity;
                if(amount >= 0){
                    Manager.econ.depositPlayer(player, amount);
                }else {
                    Manager.econ.withdrawPlayer(player, amount);
                }

            }
        });

    }

}
