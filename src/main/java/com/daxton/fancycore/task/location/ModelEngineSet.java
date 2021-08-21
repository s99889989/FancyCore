package com.daxton.fancycore.task.location;

import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Silverfish;

import java.util.Map;

public class ModelEngineSet implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //標記
        String mark = actionMapHandle.getString(new String[]{"mark","mk"},"0");
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

        if(location != null){
            LivingEntity entity = (Silverfish) location.getWorld().spawnEntity(location, EntityType.SILVERFISH);
            entity.setCollidable(false);
            entity.setAI(false);
            entity.setCustomName("ModleEngine");
            //ModeledEntity modeledEntity = ModelEngineAPI.api.getModelManager().getModeledEntity(entity.getUniqueId());




        }


    }

}
