package com.daxton.fancycore.api.aims.vector;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class PathDeviation {

    //偏轉
    public static Vector getDeviation(Location sourceLocation, boolean pt, boolean yw, boolean sign, double hight, double angle, double distance){
        Random random = new Random();
        if(sign){
            angle *= random.nextBoolean() ? 1 : -1;
        }
        double pitch;
        if(pt){
            pitch = ((sourceLocation.getPitch() + 90 + (hight*-1)) * Math.PI) / 180;
        }else {
            pitch = ((90 + (hight*-1)) * Math.PI) / 180;
        }
        double yaw;
        if(yw){
            yaw  = ((sourceLocation.getYaw() + 90 + angle)  * Math.PI) / 180;
        }else {
            yaw  = (90 + (angle)  * Math.PI) / 180;
        }

        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.cos(pitch);
        double z = Math.sin(pitch) * Math.sin(yaw);

        Vector vector = new Vector(x, y, z).multiply(distance);


        return vector;
    }

    //雜項
    public static Vector getDirection(Location dirLocation, boolean pt, boolean yw, double hight, double angle, double distance){

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

}
