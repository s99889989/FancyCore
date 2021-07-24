package com.daxton.fancycore.api.aims.entity.judgment;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class Range {

    //判斷該目標是否在該座標範圍內
    public static boolean isIn(Location location, LivingEntity targetEntity, double radius){
        boolean out = false;
        double sX = location.getX();
        double sY = location.getY();
        double sZ = location.getZ();

        double tX = targetEntity.getLocation().getX();
        double tY = targetEntity.getLocation().getY();
        double tZ = targetEntity.getLocation().getZ();

        double dd = Math.sqrt(Math.pow((tX-sX),2) + Math.pow((tY-sY),2) +Math.pow((tZ-sZ),2));
        if(dd <= radius){
            out = true;
        }
        return out;
    }

}
