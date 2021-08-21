package com.daxton.fancycore.task.meta;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class Action implements FancyAction {

    public Action(){

    }

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String inTaskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //動作列表
        List<Map<String, String>> action_Map_List = actionMapHandle.getActionMapList(new String[]{"a","action"}, null);

        //動作標記
        String taskID = actionMapHandle.getString(new String[]{"m","mark"}, inTaskID);

        //動作次數
        int count = actionMapHandle.getInt(new String[]{"c","count"}, 1);

        //動作間隔
        int countPeriod = actionMapHandle.getInt(new String[]{"cp","countPeriod"},10);

        //需要目標才執行
        boolean needTarget = actionMapHandle.getBoolean(new String[]{"nt","needTarget"},false);

        //暫停持續執行的動作
        boolean stop = actionMapHandle.getBoolean(new String[]{"stop","s"}, false);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> targetList = GetEntity.get(self, target, targetMap);

        if(count > 1){
            int delay = 0;
            for(int i = 0 ; i < count; i++){

                new BukkitRunnable(){

                    @Override
                    public void run() {
                        if(targetList.size() > 0){
                            for(LivingEntity livingEntity : targetList){
                                MapGetKey actionMapHandle2 = new MapGetKey(action_Map, self, livingEntity);
                                //動作標記
                                String taskID = actionMapHandle2.getString(new String[]{"m","mark"}, String.valueOf((int)(Math.random()*100000)));
                                StartAction.execute(self, livingEntity, taskID, action_Map_List, stop);

                            }
                        }else {
                            if(!needTarget){
                                StartAction.execute(self, null, taskID, action_Map_List, stop);
                            }
                        }

                    }
                }.runTaskLater(FancyCore.fancyCore, delay);
                delay += countPeriod;
            }
        }else{
            if(targetList.size() > 0){
                for(LivingEntity livingEntity : targetList){
                    MapGetKey actionMapHandle2 = new MapGetKey(action_Map, self, livingEntity);
                    //動作標記
                    String taskID2 = actionMapHandle2.getString(new String[]{"m","mark"}, String.valueOf((int)(Math.random()*100000)));
                    StartAction.execute(self, livingEntity, taskID2, action_Map_List, stop);
                }
            }else {
                if(!needTarget){
                    StartAction.execute(self, null, taskID, action_Map_List, stop);
                }
            }

        }

    }

}
