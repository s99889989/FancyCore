package com.daxton.fancycore.task.meta.run;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.many.Radius;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.api.aims.vector.LocationVector;
import com.daxton.fancycore.api.aims.vector.PathDeviation;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.task.meta.StartAction;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OrbitalAction extends BukkitRunnable {

    private Function<Location,Location> fLocation;
    private Location targetLocation;
    private Location startLocation;
    private Vector vec;
    private int speed;
    private int j;

    private int period = 1;
    private int duration = 100;

    private List<Map<String, String>> onTimeHitList = new ArrayList<>();
    private List<Map<String, String>> onTimeList = new ArrayList<>();
    private List<Map<String, String>> onEndHitList = new ArrayList<>();
    private List<Map<String, String>> onEndList = new ArrayList<>();

    private double hitRange = 0.8;
    private boolean selfDead = true;
    private boolean targetDead = true;
    private int setHitCount;
    private int hitCount;
    private boolean hitStop;

    private LivingEntity self;

    private LivingEntity target;

    private String taskID = "";

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String inTaskID){
        this.self = self;
        this.target = target;
        this.taskID = inTaskID;
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //初始動作
        List<Map<String, String>> onStartList = actionMapHandle.getActionMapList(new String[]{"onstart","os"},null);

        //過程中動作
        onTimeList = actionMapHandle.getActionMapList(new String[]{"ontime","ot"},null);

        //過程中命中動作
        onTimeHitList = actionMapHandle.getActionMapList(new String[]{"ontimehit","oth"},null);

        //最後動作
        onEndList = actionMapHandle.getActionMapList(new String[]{"onend","oe"},null);

        //最後命中動作
        onEndHitList = actionMapHandle.getActionMapList(new String[]{"onendhit","oeh"},null);

        //執行間隔時間
        period = actionMapHandle.getInt(new String[]{"period","p"},0);

        //此動作要持續多久
        duration = actionMapHandle.getInt(new String[]{"duration","d"},20);

        //軌道偏移
        String[] orbitalDeviation = actionMapHandle.getStringList(new String[]{"orbitaldeviation","od"},new String[]{"true","true","false","0","0"},"\\|",5);

        boolean odpt = true;
        boolean odyw = true;
        boolean odsign = false;
        double odX = 0;
        double odY = 0;

        if(orbitalDeviation.length == 5){
            odpt = Boolean.parseBoolean(orbitalDeviation[0]);
            odyw = Boolean.parseBoolean(orbitalDeviation[1]);
            odsign = Boolean.parseBoolean(orbitalDeviation[2]);
            try {
                odX = Double.parseDouble(orbitalDeviation[3]);
                odY = Double.parseDouble(orbitalDeviation[4]);

            }catch (NumberFormatException exception){
                odX = 0;
                odY = 0;

            }
        }

        //技能運行速度
        speed = actionMapHandle.getInt(new String[]{"speed","s"},20);

        //命中判定範圍
        hitRange = actionMapHandle.getDouble(new String[]{"hitrange","hr"},0.8);

        //命中次數
        setHitCount = actionMapHandle.getInt(new String[]{"hitcount","hc"},0);
        //命中次數過後是否結束任務
        hitStop = actionMapHandle.getBoolean(new String[]{"hitstop","hs"},false);

        //起始座標偏移
        String[] startlocadds = actionMapHandle.getStringList(new String[]{"startlocadd","sla"},new String[]{"true","true","0","0","0","0"},"\\|",6);
        boolean startTargetPitch = Boolean.parseBoolean(startlocadds[0]);
        boolean startTargetYaw = Boolean.parseBoolean(startlocadds[1]);
        double startPitch;
        double startYaw;
        double startDistance;
        double startHight;
        try {
            startPitch = Double.parseDouble(startlocadds[2]);
            startYaw = Double.parseDouble(startlocadds[3]);
            startDistance = Double.parseDouble(startlocadds[4]);
            startHight = Double.parseDouble(startlocadds[5]);
        }catch (NumberFormatException exception){
            startPitch = 0;
            startYaw = 0;
            startDistance = 0;
            startHight = 0;
        }
        //起始位置
        startLocation = LocationVector.getDirectionLoction(self.getLocation(), self.getLocation(), startTargetPitch, startTargetYaw, startPitch, startYaw, startDistance).add(0, startHight, 0);

        //如果自身死亡任務是否取消
        selfDead = actionMapHandle.getBoolean(new String[]{"selfdead","sd"},true);

        //如果目標死亡任務是否取消
        targetDead = actionMapHandle.getBoolean(new String[]{"targetdead","td"},true);

        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);
        targetLocation = location;

        if(targetLocation != null){

            fLocation = (floc) -> floc;

            vec = PathDeviation.getDirection2(self.getLocation(), odpt, odyw, odsign, odX, odY, 1);
            
            StartAction.execute(self, target, onStartList, startLocation, taskID);

            runTaskTimer(FancyCore.fancyCore, 0L, period);


        }




    }

    public void run(){
        //自身死亡
        if(selfDead && self.isDead()){

            StartAction.execute(this.self, this.target, onEndList, targetLocation.clone() , taskID);

            cancel();
            TaskActionManager.task_OrbitalAction_Map.remove(taskID);
            return;
        }

        //目標死亡
        if(target != null && targetDead && target.isDead()){

            StartAction.execute(this.self, this.target, onEndList, targetLocation, taskID);

            cancel();
            TaskActionManager.task_OrbitalAction_Map.remove(taskID);
            return;
        }


        j += period;
        for(int k = 0; k < 1; ++k) {
            double c = Math.min(1.0D, (double) j / speed);
            Location location = fLocation.apply(startLocation.add(targetLocation.clone().subtract(startLocation).toVector().normalize().multiply(c).add(vec.multiply(1.0D - c))));
            StartAction.execute(this.self, this.target, onTimeList, location, taskID);
        }
        if(setHitCount == 0 || hitCount < setHitCount){
            List<LivingEntity> livingEntityList = Radius.getRadiusLivingEntities3(self, startLocation, hitRange);
            if(livingEntityList.size() > 0){
                hitCount++;
                if(hitStop && hitCount >= setHitCount){
                    cancel();
                }
                for(LivingEntity livingEntity : livingEntityList){
                    StartAction.execute(this.self, livingEntity, onTimeHitList, targetLocation, taskID);
                }
            }

        }

        if(target != null){

            if(j > duration || startLocation.distanceSquared(targetLocation) < hitRange){

                StartAction.execute(this.self, this.target, onEndList, targetLocation, taskID);

                cancel();
                TaskActionManager.task_OrbitalAction_Map.remove(taskID);

            }else if(startLocation.distanceSquared(target.getEyeLocation()) < hitRange){

                StartAction.execute(this.self, this.target, onEndHitList, targetLocation, taskID);

                cancel();
                TaskActionManager.task_OrbitalAction_Map.remove(taskID);

            }
        }else {
            if(j > duration || startLocation.distanceSquared(targetLocation) < hitRange){

                StartAction.execute(this.self, this.target, onEndList, targetLocation, taskID);

                cancel();
                TaskActionManager.task_OrbitalAction_Map.remove(taskID);
            }
        }

    }

}
