package com.daxton.fancycore.task.meta.run;

import com.daxton.fancycore.api.task.GuiseEntity;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class OrbitalAction extends BukkitRunnable {

    private Location sourceLocation;
    private Location targetLocation;
    private GuiseEntity guiseEntity;


    public OrbitalAction(GuiseEntity guiseEntity, Location sourceLocation, Location targetLocation){
        this.guiseEntity = guiseEntity;
        this.sourceLocation = sourceLocation;
        this.targetLocation = targetLocation;
    }

    //偏轉
    public static Vector getDirection2(Location dirLocation, boolean pt, boolean yw, boolean sign, double hight, double angle, double distance){
        Random random = new Random();
        if(sign){
            angle *= (double)(random.nextBoolean() ? 1 : -1);
        }
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


        return vector;
    }

    public void run(){

    }

}
