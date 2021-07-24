package com.daxton.fancycore.api.aims.entity.one;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.List;

public class LookTarget {

    //獲得目視生命目標
    public static LivingEntity getLivingTarget(LivingEntity self, double radius) {
        List<Entity> targetEntityList = self.getNearbyEntities(radius, radius, radius);
        LivingEntity target = null;
        if(targetEntityList.size() > 0){
            double min = radius+1;
            for(Entity targetEntity : targetEntityList){
                if(targetEntity instanceof LivingEntity){

                    Vector targetVector = ((LivingEntity) targetEntity).getEyeLocation().subtract(self.getEyeLocation()).toVector();
                    double rad = targetVector.angle(self.getEyeLocation().getDirection());
                    if(rad < 0.2){
                        Location st = targetEntity.getLocation().subtract(self.getLocation());
                        double dd = Math.sqrt(Math.pow((st.getX()),2) + Math.pow((st.getY()),2) +Math.pow((st.getZ()),2));
                        if(dd <= radius && dd < min){
                            min = dd;
                            target = (LivingEntity) targetEntity;
                        }
                    }
                }
            }
        }
        return target;
    }

}
