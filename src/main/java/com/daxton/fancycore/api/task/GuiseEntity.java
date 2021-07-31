package com.daxton.fancycore.api.task;


import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.nms.ArmorStandOther;
import com.daxton.fancycore.nms.v1_17_R1.ArmorStand;



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GuiseEntity {

    private int entityID;
    private Location location;
    private UUID uuid;
    private EntityType entityType;

    public static GuiseEntity createGuise(Location location, String entityTypeName, boolean living, boolean pitch, boolean yaw){
        return new GuiseEntity(location, entityTypeName, living, pitch, yaw);
    }
    //建立目標
    public GuiseEntity(Location inputLocation, String entityTypeName, boolean living, boolean pitch, boolean yaw){
        int entityID = (int)(Math.random() * Integer.MAX_VALUE);
        UUID uuid = UUID.randomUUID();
        this.entityID = entityID;
        this.location = inputLocation;
        this.uuid = uuid;
        if(living){
            this.entityType = PackEntity.livingEntitySpawn(entityID, uuid, entityTypeName, inputLocation, pitch, yaw);
        }else {
            this.entityType = PackEntity.entitySpawn(entityID, uuid, entityTypeName, inputLocation, pitch, yaw);
        }
    }

    //穿上裝備
    public void equipment(ItemStack itemStack, String itemSlot){
        PackEntity.equipmentInvisible(this.entityID, itemStack, itemSlot);
    }
    //騎乘目標
    public void mount(int targetID){
        PackEntity.mount(this.entityID, targetID);
    }

    //傳送目標
    public void teleport(Location inputLocation, boolean pitch, boolean yaw){
        PackEntity.teleport(this.entityID, inputLocation, pitch, yaw);
        this.location = inputLocation;
    }
    //修改目標方向
    public void direction(double pitchAdd, double yawAdd, boolean pitch, boolean yaw){
        PackEntity.direction(this.entityID, pitchAdd, yawAdd, pitch, yaw);
    }
    //觀看方向
    public void look(double yawAdd){
        PackEntity.look(this.entityID, yawAdd);
    }
    //頭角度
    public void headRotation(double yawAdd){
        PackEntity.headRotation(this.entityID, yawAdd);
    }
    //修改顯示名稱
    public void setName(String text){
        PackEntity.setName(this.entityID, text);
    }

    //改變目標生物向量
    public void velocity(Vector vector){
        PackEntity.velocity(this.entityID, vector);
    }
    //設置目標生物是否隱形
    public void setVisible(boolean b){
        if(b){
            PackEntity.entityInvisible(this.entityID);
        }else {
            PackEntity.entityEmpty(this.entityID);
        }
    }

    //刪除目標生物
    public void delete(){
        PackEntity.delete(this.entityID);
    }
    public static double square(double num) {
        return num * num;
    }

    //調整盔甲架角度
    public void setArmorStandAngle(String type, double x, double y, double z){
        ArmorStandOther.setArmorStandAngle(this.entityID, type, x, y, z);
    }

    //設為小盔甲架
    public void smallArmorStand(){
        ArmorStandOther.small(this.entityID);
    }
    //盔甲架有手臂
    public void hasArmsArmorStand(){
        ArmorStandOther.hasArms(this.entityID);
    }
    //盔甲架沒有底板
    public void noBasePlateArmorStand(){
        ArmorStandOther.noBasePlate(this.entityID);
    }
    //盔甲架發光
    public void markArmorStand(){
        ArmorStandOther.marker(this.entityID);
    }
    //清除盔甲架設置
    public void clearArmorStand(){
        ArmorStandOther.clear(this.entityID);
    }


    public Location getLocation() {
        return location;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public int getEntityID() {
        return entityID;
    }
}
