package com.daxton.fancycore.api.aims.location.one;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Look {
    //獲取面前指定距離目標
    public static Location getLook(LivingEntity self, double distance){

        Location location = self.getLocation();
        Vector vector = location.getDirection().multiply(distance);
        location.add(vector);

        return location;
    }

}
