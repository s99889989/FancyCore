package com.daxton.fancycore.task.meta;

import com.daxton.fancycore.api.task.FancyAction;
import com.daxton.fancycore.api.taskaction.MapGetKey;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public class Action implements FancyAction {

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



    }

    public void startAction(List<Map<String, String>> action_Map_List, String taskID, LivingEntity self, LivingEntity livingEntity, boolean stopB){




    }

}
