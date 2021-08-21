package com.daxton.fancycore.task.location;

import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.manager.PlayerManagerCore;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

import static org.bukkit.Particle.*;
import static org.bukkit.Particle.ITEM_CRACK;

public class ParticlesSend implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);

        //獲得功能
        String function = actionMapHandle.getString(new String[]{"function","fc"},"");
        //粒子名稱
        Particle putParticle = actionMapHandle.getParticle(new String[]{"particle","p"},"REDSTONE");
        //粒子的方塊材質
        BlockData blockData = actionMapHandle.getBlockData(new String[]{"particledata","pdata"},"REDSTONE_BLOCK");
        //粒子的物品材質
        ItemStack itemData = actionMapHandle.getItemStack(new String[]{"particledata","pdata"},"COOKIE");
        //粒子的顏色
        Particle.DustOptions color = actionMapHandle.getParticleColor(new String[]{"particledata","pdata"},"FF0000");
        //粒子的速度
        double extra = actionMapHandle.getDouble(new String[]{"extra","e"},0);
        //粒子的數量
        int count = actionMapHandle.getInt(new String[]{"count","c"},10);
        //粒子擴散
        String[] locOffs = actionMapHandle.getStringList(new String[]{"locationoffset","locoff"},new String[]{"0","0","0"},"\\|",3);
        double xOffset = 0;
        double yOffset = 0;
        double zOffset = 0;
        try {
            xOffset = Double.parseDouble(locOffs[0]);
            yOffset = Double.parseDouble(locOffs[1]);
            zOffset = Double.parseDouble(locOffs[2]);
        }catch (NumberFormatException exception){
            xOffset = 0;
            yOffset = 0;
            zOffset = 0;
        }
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);

        if(function.toLowerCase().contains("remove")){
            if(self instanceof Player){
                Player player = (Player) self;
                UUID uuid = player.getUniqueId();

                //移除粒子效果列表
                PlayerDataFancy playerDataFancy = PlayerManagerCore.player_Data_Map.get(uuid);
                if(playerDataFancy.particles_remove.contains(putParticle.toString())){
                    playerDataFancy.particles_remove.remove(putParticle.toString());
                }else {
                    playerDataFancy.particles_remove.add(putParticle.toString());
                }
            }
        }else {
            if(location != null){
                sendParticle(self, putParticle, location, count, xOffset, yOffset, zOffset, extra, color, blockData, itemData);
            }
        }

    }

    //單點
    public static void sendParticle(LivingEntity self, Particle putParticle, Location location, int count, double xOffset, double yOffset, double zOffset, double extra, Particle.DustOptions color, BlockData blockData, ItemStack itemData){
        try{
            if(putParticle == REDSTONE){
                self.getWorld().spawnParticle(putParticle, location, count, xOffset, yOffset, zOffset, extra,color);
            }else if(putParticle == BLOCK_CRACK || putParticle == BLOCK_DUST){
                self.getWorld().spawnParticle(putParticle, location, count, xOffset, yOffset, zOffset, extra,blockData);
            }else if(putParticle == ITEM_CRACK ){
                self.getWorld().spawnParticle(ITEM_CRACK, location, count, xOffset, yOffset, zOffset, extra,itemData);
            } else {
                self.getWorld().spawnParticle(putParticle, location, count, xOffset, yOffset, zOffset, extra);
            }
        }catch (Exception exception){

        }


    }


}
