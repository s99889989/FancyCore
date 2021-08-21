package com.daxton.fancycore.api.aims.location;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.one.LookTarget;
import com.daxton.fancycore.api.aims.vector.LocationVector;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetLocation {

    public static Location getOne(LivingEntity self, LivingEntity target, Map<String, String> targetMap, Location locationInput){
        MapGetKey mapGetKey = new MapGetKey(targetMap);
        //瞄準目標
        String targetKey = mapGetKey.getString(new String[]{"targettype"},"null");

        //距離
        double distance = mapGetKey.getDouble(new String[]{"distance","d"},0);

        if(target == null){
            target = LookTarget.getLivingTarget(self, distance);
        }

        //座標向量偏移
        String[] vecAdds = mapGetKey.getStringList(new String[]{"VectorAdd","va"},new String[]{"null","true","true","0","0","0"},"\\|",6);
        String directionT = vecAdds[0];
        boolean targetPitch = Boolean.parseBoolean(vecAdds[1]);
        boolean targetYaw = Boolean.parseBoolean(vecAdds[2]);
        double addPitch;
        double addYaw;
        double addDistance;
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
        String[] locAdds = mapGetKey.getStringList(new String[]{"LocationAdd","la"},new String[]{"0","0","0"},"\\|",3);
        double addX;
        double addY;
        double addZ;
        try {
            addX = Double.parseDouble(locAdds[0]);
            addY = Double.parseDouble(locAdds[1]);
            addZ = Double.parseDouble(locAdds[2]);
        }catch (NumberFormatException exception){
            addX = 0;
            addY = 0;
            addZ = 0;
        }
        //世界設定
        String worldName = mapGetKey.getString(new String[]{"wn","worldname"},"self");
        Location location = null;
        switch (targetKey){
            case "loctarget":
                if(target != null){
                    location = target.getLocation().add(addX, addY, addZ);
                }
                break;
            case "locadd":
                if(locationInput != null){
                    location = locationInput.add(addX, addY, addZ);
                }
                break;
            case "locworld":
                if(worldName.equals("self")){
                    location = new Location(self.getWorld(), addX, addY, addZ);
                }else {
                    location = new Location(Bukkit.getWorld(worldName), addX, addY, addZ);
                }
                break;
            case "locself":

            default:
                location = self.getLocation().add(addX, addY, addZ);
                break;
        }

        if(location != null && !directionT.equals("null")){
            if(directionT.toLowerCase().contains("target") && target != null){
                location = LocationVector.getDirectionLoction(location, target.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance);
            }else {
                location = LocationVector.getDirectionLoction(location, self.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance);
            }
        }


        //定點是否要在方塊上。
        boolean onblock = mapGetKey.getBoolean(new String[]{"onblock","ob"}, false);
        if(onblock && location != null){
            while(!(location.getBlock().getType() == Material.AIR)){
                location.setY(location.getBlockY()+1);

            }
            while((location.getBlock().getType() == Material.AIR)){
                location.setY(location.getY()-0.1);

            }
            while(!(location.getBlock().getType() == Material.AIR)){
                location.setY(location.getBlockY()+1);

            }
        }

        if(location == null){
            location = locationInput;
        }


        return location;
    }

    public static List<Location> getMultiple(LivingEntity self, LivingEntity target, Map<String, String> targetMap, Location locationInput){
        List<Location> locationList = new ArrayList<>();
        MapGetKey mapGetKey = new MapGetKey(targetMap);
        //瞄準目標
        String targetKey = mapGetKey.getString(new String[]{"targettype"},"null");
        //距離
        double distance = mapGetKey.getDouble(new String[]{"distance","d"},0);
        //座標向量偏移
        String[] vecAdds = mapGetKey.getStringList(new String[]{"VectorAdd","va"},new String[]{"null","true","true","0","0","0"},"\\|",6);
        String directionT = vecAdds[0];
        boolean targetPitch = Boolean.parseBoolean(vecAdds[1]);
        boolean targetYaw = Boolean.parseBoolean(vecAdds[2]);
        double addPitch;
        double addYaw;
        double addDistance;
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
        String[] locAdds = mapGetKey.getStringList(new String[]{"LocationAdd","la"},new String[]{"0","0","0"},"\\|",3);
        double addX;
        double addY;
        double addZ;
        try {
            addX = Double.parseDouble(locAdds[0]);
            addY = Double.parseDouble(locAdds[1]);
            addZ = Double.parseDouble(locAdds[2]);
        }catch (NumberFormatException exception){
            addX = 0;
            addY = 0;
            addZ = 0;
        }
        //世界設定
        String worldName = mapGetKey.getString(new String[]{"wn","worldname"},"self");
        Location location = null;
        switch (targetKey){
            case "locself":
                if(directionT.toLowerCase().contains("target") && target != null){
                    location = LocationVector.getDirectionLoction(self.getLocation(), target.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                }else {
                    location = LocationVector.getDirectionLoction(self.getLocation(), self.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                }
                break;
            case "loctarget":
                if(target != null){
                    if(directionT.toLowerCase().contains("target")){
                        location = LocationVector.getDirectionLoction(target.getLocation(), target.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                    }else {
                        location = LocationVector.getDirectionLoction(target.getLocation(), self.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                    }
                }
                break;
            case "locadd":
                if(locationInput != null){
                    if(directionT.toLowerCase().contains("target") && target != null){
                        location = LocationVector.getDirectionLoction(locationInput, target.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                    }else if(directionT.toLowerCase().contains("self")){
                        location = LocationVector.getDirectionLoction(locationInput, self.getLocation(), targetPitch, targetYaw, addPitch, addYaw, addDistance).add(addX, addY, addZ);
                    }else {
                        location = locationInput.add(addX, addY, addZ);
                    }
                }
                break;
            case "locworld":
                if(worldName.equals("self")){
                    location = new Location(self.getWorld(), addX, addY, addZ);
                }else {
                    location = new Location(Bukkit.getWorld(worldName), addX, addY, addZ);
                }

        }

        //定點是否要在方塊上。
        boolean onblock = mapGetKey.getBoolean(new String[]{"onblock","ob"}, false);
        if(onblock && location != null){
            while(!(location.getBlock().getType() == Material.AIR)){
                location.setY(location.getBlockY()+1);

            }
            while((location.getBlock().getType() == Material.AIR)){
                location.setY(location.getY()-0.1);

            }
            while(!(location.getBlock().getType() == Material.AIR)){
                location.setY(location.getBlockY()+1);

            }
        }

        if(location == null){
            location = locationInput;
        }


        return locationList;
    }

}
