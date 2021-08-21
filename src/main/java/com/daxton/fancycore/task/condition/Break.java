package com.daxton.fancycore.task.condition;

import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class Break {

    //Condition->Health
    public static Map<String, HealthChange> condition_HealthChange_Map = new HashMap<>();

    public static boolean valueOf(LivingEntity self, LivingEntity target, Map<String, String> action_Map, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //相反
        boolean mode = actionMapHandle.getBoolean(new String[]{"Mode","m"}, false);
        //比對類型
        String conditionType = actionMapHandle.getString(new String[]{"ConditionType","ct"}, "");
        //比對條件
        String conditionContent = actionMapHandle.getString(new String[]{"ConditionContent","cp"}, "");
        boolean result = false;
        switch (conditionType.toLowerCase()){
            case "compare":
                result = Compare.valueOf(self, target, conditionContent, taskID);
                break;
            case "contains":
                result = Contains.valueOf(self, target, conditionContent, taskID);
                break;
            case "equals":
                result = Equals.valueOf(self, target, conditionContent, taskID);
                break;
            case "healthchange":
                if(condition_HealthChange_Map.get(taskID) == null){
                    condition_HealthChange_Map.put(taskID, new HealthChange(self, target, conditionContent, taskID));
                    condition_HealthChange_Map.get(taskID).chickHealth();
                    result = condition_HealthChange_Map.get(taskID).isResult();
                }else {
                    condition_HealthChange_Map.get(taskID).chickHealth();
                    result = condition_HealthChange_Map.get(taskID).isResult();
                }
                break;
        }
        if(mode){
            if(result){
                result = false;
            }else {
                result = true;
            }
        }

        return result;

    }

}
