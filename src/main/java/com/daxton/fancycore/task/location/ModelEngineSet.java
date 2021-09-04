package com.daxton.fancycore.task.location;

import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.modelentity.ModelEntity;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.util.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Silverfish;

import java.util.Map;

public class ModelEngineSet implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        if (Bukkit.getServer().getPluginManager().getPlugin("ModelEngine") == null){
            return;
        }
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //標記
        String mark = actionMapHandle.getString(new String[]{"mark","mk"},"");
        //模型ID
        String modelid = actionMapHandle.getString(new String[]{"m", "mid", "model", "modelid"}, null);
        //模型姿態
        String[] state = actionMapHandle.getStringList(new String[]{"s","state"},new String[]{"idle","0","1","1"},"\\|",4);
        String stateID = state[0];
        int lerpIn = 0;
        int lerpOut = 1;
        double speed = 1;
        try {
            lerpIn = Integer.parseInt(state[1]);
            lerpOut = Integer.parseInt(state[2]);
            speed = Double.parseDouble(state[3]);
        }catch (NumberFormatException exception){
            lerpIn = 0;
            lerpOut = 1;
            speed = 1;
        }
        //是否要移動
        boolean teleport = actionMapHandle.getBoolean(new String[]{"teleport","tp"}, false);
        //是否要刪除
        boolean delete = actionMapHandle.getBoolean(new String[]{"delete","d"}, false);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);

        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);

        if(TaskActionManager.task_ModelEntity_Map.get(taskID+mark) == null){
            if(location != null && !delete){
                ModelEntity modelEntity = new ModelEntity(location, modelid, stateID);
                TaskActionManager.task_ModelEntity_Map.put(taskID+mark, modelEntity);
            }
        }else {
            ModelEntity modelEntity = TaskActionManager.task_ModelEntity_Map.get(taskID+mark);

            if(!modelEntity.stateID.equalsIgnoreCase(stateID)){
                modelEntity.setState(stateID, lerpIn, lerpOut, speed);
            }

            if(teleport){
                if(location != null){
                    modelEntity.teleport(location);
                }
            }

            if(delete){
                modelEntity.delete();
                TaskActionManager.task_ModelEntity_Map.remove(taskID+mark);
            }

        }

    }

}
