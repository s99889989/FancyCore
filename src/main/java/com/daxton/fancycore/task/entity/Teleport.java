package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.api.aims.vector.LocationVector;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.Map;

public class Teleport implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle2 = new MapGetKey(action_Map, self, target);

        //目標
        String targetString = actionMapHandle2.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);


        livingEntityList.forEach(livingEntity -> {

            MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

            //座標向量偏移
            String[] vecAdds = actionMapHandle.getStringList(new String[]{"VectorAdd","va"},new String[]{"self","true","true","0","0","0"},"\\|",6);
            String directionT = vecAdds[0];
            boolean targetPitch = Boolean.parseBoolean(vecAdds[1]);
            boolean targetYaw = Boolean.parseBoolean(vecAdds[2]);
            double addPitch = 0;
            double addYaw = 0;
            double addDistance = 0;
            try {
                addPitch = Double.parseDouble(vecAdds[3]);
                addYaw = Double.parseDouble(vecAdds[4]);
                addDistance = Double.parseDouble(vecAdds[5]);
            }catch (NumberFormatException exception){
                addPitch = 0;
                addYaw = 0;
                addDistance = 0;
            }

            //座標偏移
            String[] locAdds = actionMapHandle.getStringList(new String[]{"LocationAdd","la"},new String[]{"0","0","0"},"\\|",3);
            double addX = 0;
            double addY = 0;
            double addZ = 0;
            try {
                addX = Double.parseDouble(locAdds[0]);
                addY = Double.parseDouble(locAdds[1]);
                addZ = Double.parseDouble(locAdds[2]);
            }catch (NumberFormatException exception){
                addX = 0;
                addY = 0;
                addZ = 0;
            }
            //在方塊上
            boolean onblock = actionMapHandle.getBoolean(new String[]{"onblock","ob"}, false);

            Location location = null;
            if(target != null && directionT.toLowerCase().contains("target")){
                location = LocationVector.getDirectionLoction(livingEntity.getLocation(), livingEntity.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
            }else {
                location = LocationVector.getDirectionLoction(livingEntity.getLocation(), self.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
            }

            if(onblock && location != null){
                while(!location.getBlock().getTranslationKey().equals("block.minecraft.air")){
                    location.setY(location.getBlockY()+1);

                }
                while(location.getBlock().getTranslationKey().equals("block.minecraft.air")){
                    location.setY(location.getY()-0.1);

                }
                while(!location.getBlock().getTranslationKey().equals("block.minecraft.air")){
                    location.setY(location.getBlockY()+1);

                }
            }
            //傳送
            livingEntity.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        });


    }

}
