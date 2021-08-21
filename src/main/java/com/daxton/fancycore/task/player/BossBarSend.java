package com.daxton.fancycore.task.player;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class BossBarSend implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        String mark = actionMapHandle.getString(new String[]{"mark","mk"},"");

        BarStyle style = actionMapHandle.getBarStyle(new String[]{"s", "style"},"SOLID");

        BarColor color = actionMapHandle.getBarColor(new String[]{"c", "color"},"BLUE");

        BarFlag flag = actionMapHandle.getBarFlag(new String[]{"f", "flag"});

        double progress = actionMapHandle.getDouble(new String[]{"p", "progress"},-1);

        boolean delete = actionMapHandle.getBoolean(new String[]{"d","delete","deleteAll"},false);

        String message = actionMapHandle.getString(new String[]{"message","m"}, null);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);

        if(TaskActionManager.task_BossBar_Map.get(taskID+mark) == null){
            if(message != null && color != null && style != null){

                BossBar bossBar = Bukkit.createBossBar(message, color, style, flag);
                livingEntityList.forEach(livingEntity -> {
                    if(livingEntity instanceof Player){
                        Player player = (Player) livingEntity;
                        bossBar.addPlayer(player);
                    }
                });
                if(progress > 0.0 && progress < 1.0000000001){
                    bossBar.setProgress(progress);
                }

                TaskActionManager.task_BossBar_Map.put(taskID+mark, bossBar);
            }
        }else {
            BossBar bossBar = TaskActionManager.task_BossBar_Map.get(taskID+mark);
            if(message != null){
                bossBar.setTitle(message);
            }

            if(color != null){
                bossBar.setColor(color);
            }
            if(style != null){
                bossBar.setStyle(style);
            }
            if(progress > 0.0 && progress < 1.0000000001){
                bossBar.setProgress(progress);
            }

            if(delete){
                bossBar.removeAll();
                TaskActionManager.task_BossBar_Map.remove(taskID+mark);
                return;
            }
            TaskActionManager.task_BossBar_Map.put(taskID+mark, bossBar);
        }
    }

}
