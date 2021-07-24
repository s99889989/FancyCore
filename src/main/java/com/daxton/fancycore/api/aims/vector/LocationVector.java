package com.daxton.fancycore.api.aims.vector;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationVector {

    //獲取向量座標
    public static Location getDirectionLoction(Location inputLocation, Location dirLocation, boolean pt, boolean yw, double hight, double angle, double distance){

        Location location = new Location(inputLocation.getWorld(),inputLocation.getX(),inputLocation.getY(),inputLocation.getZ());

        double pitch;
        if(pt){
            pitch = ((dirLocation.getPitch() + 90 + (hight*-1)) * Math.PI) / 180;
        }else {
            pitch = ((90 + (hight*-1)) * Math.PI) / 180;
        }
        double yaw;
        if(yw){
            yaw  = ((dirLocation.getYaw() + 90 + angle)  * Math.PI) / 180;
        }else {
            yaw  = (90 + (angle)  * Math.PI) / 180;
        }

        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.cos(pitch);
        double z = Math.sin(pitch) * Math.sin(yaw);

        Vector vector = new Vector(x, y, z).multiply(distance);
        location.add(vector);
        location.setDirection(vector);

        return location;
    }

}
