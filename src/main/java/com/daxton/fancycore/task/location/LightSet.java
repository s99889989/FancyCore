package com.daxton.fancycore.task.location;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;
import ru.beykerykt.lightapi.chunks.ChunkInfo;

import java.util.Map;

public class LightSet implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        if (Bukkit.getServer().getPluginManager().getPlugin("LightAPI") == null) {
            return;
        }
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //發光等級
        int lightLevel = actionMapHandle.getInt(new String[]{"lightlevel","ll"},0);
        //顯示的時間
        int duration = actionMapHandle.getInt(new String[]{"duration","dt"},-1);
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);

        if(location != null){

            createLight(location, lightLevel, true);

            if(duration > 0){
                BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                    @Override
                    public void run() {

                        deleteLight(location, true);

                    }
                };
                bukkitRunnable.runTaskLater(FancyCore.fancyCore, duration);
            }else if(duration < 0){

                deleteLight(location, true);

            }

        }

    }

    private static void createLight(Location location, int level, boolean flag) {

        LightAPI.createLight(location, LightType.BLOCK, level, true);
        up(location);
    }

    private static void deleteLight(Location location, boolean flag) {

        LightAPI.deleteLight(location, LightType.BLOCK, true);

        up(location);
    }

    private static void up(Location location){
        for(ChunkInfo info: LightAPI.collectChunks(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ())){
            LightAPI.updateChunks(info);
        }
    }

}
