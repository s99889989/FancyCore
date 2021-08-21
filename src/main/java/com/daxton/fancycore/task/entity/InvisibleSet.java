package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.PackEntity;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class InvisibleSet implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //目標隱形
        boolean entityB = actionMapHandle.getBoolean(new String[]{"entity","e"},true);
        //主手隱形
        boolean mainHand = actionMapHandle.getBoolean(new String[]{"MainHand","mh"},true);
        //副手隱形
        boolean offHand = actionMapHandle.getBoolean(new String[]{"OffHand","oh"},true);
        //頭盔隱形
        boolean head = actionMapHandle.getBoolean(new String[]{"Head","h"},true);
        //盔甲隱形
        boolean chest = actionMapHandle.getBoolean(new String[]{"Chest","c"},true);
        //褲子隱形
        boolean legs = actionMapHandle.getBoolean(new String[]{"Legs","l"},true);
        //靴子隱形
        boolean feet = actionMapHandle.getBoolean(new String[]{"Feet","f"},true);
        //持續時間
        int duration = actionMapHandle.getInt(new String[]{"duration","dt"},40);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);


        livingEntityList.forEach(livingEntity -> {
            int entityID = livingEntity.getEntityId();
            Player player = null;
            if(livingEntity instanceof Player){
                player = (Player) livingEntity;
            }
            if(entityB){//目標隱形
                PackEntity.entityInvisible(entityID);
            }
            if(mainHand){//主手隱形
                PackEntity.equipmentInvisible(entityID, null, "MAINHAND", player);
            }
            if(offHand){//副手隱形
                PackEntity.equipmentInvisible(entityID, null, "OFFHAND", player);
            }
            if(head){//頭盔隱形
                PackEntity.equipmentInvisible(entityID, null, "HEAD", player);
            }
            if(chest){//盔甲隱形
                PackEntity.equipmentInvisible(entityID, null, "CHEST", player);
            }
            if(legs){//褲子隱形
                PackEntity.equipmentInvisible(entityID, null, "LEGS", player);
            }
            if(feet){//靴子隱形
                PackEntity.equipmentInvisible(entityID, null, "FEET", player);
            }

        });

        if(duration > 0){
            new BukkitRunnable() {
                @Override
                public void run() {
                    livingEntityList.forEach(livingEntity -> {
                        int entityID = livingEntity.getEntityId();
                        Player player = null;
                        if(livingEntity instanceof Player){
                            player = (Player) livingEntity;
                        }
                        PackEntity.entityEmpty(entityID);
                        ItemStack itemMainHand = livingEntity.getEquipment().getItemInMainHand();
                        ItemStack itemOffHand = livingEntity.getEquipment().getItemInOffHand();
                        ItemStack itemHead = livingEntity.getEquipment().getHelmet();
                        ItemStack itemChest = livingEntity.getEquipment().getChestplate();
                        ItemStack itemLegs = livingEntity.getEquipment().getLeggings();
                        ItemStack itemFeet = livingEntity.getEquipment().getBoots();
                        PackEntity.equipmentInvisible(entityID, itemMainHand, "MAINHAND", player);
                        PackEntity.equipmentInvisible(entityID, itemOffHand, "OFFHAND", player);
                        PackEntity.equipmentInvisible(entityID, itemHead, "HEAD", player);
                        PackEntity.equipmentInvisible(entityID, itemChest, "CHEST", player);
                        PackEntity.equipmentInvisible(entityID, itemLegs, "LEGS", player);
                        PackEntity.equipmentInvisible(entityID, itemFeet, "FEET", player);
                    });

                }
            }.runTaskLater(FancyCore.fancyCore, duration);
        }

    }

}
