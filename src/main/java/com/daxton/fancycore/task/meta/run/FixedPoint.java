package com.daxton.fancycore.task.meta.run;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.many.Radius;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.task.meta.StartAction;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FixedPoint extends BukkitRunnable {

	private LivingEntity self;
	private LivingEntity target;
	private String taskID;

	private Location startLocation;

	private double hitRange;
	private int hitCount;
	private boolean selfDead;

	private int period;
	private int duration;

	private List<Map<String, String>> onHitList = new ArrayList<>();
	private List<Map<String, String>> onTimeList = new ArrayList<>();
	private List<Map<String, String>> onEndList = new ArrayList<>();

	private int j;
	private int count;

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String inTaskID){
		this.taskID = inTaskID;
		this.self = self;
		this.target = target;

		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//初始動作**/
		List<Map<String, String>> onStartList = actionMapHandle.getActionMapList(new String[]{"onstart"},null);
		//過程中命中動作**/
		onHitList = actionMapHandle.getActionMapList(new String[]{"onhit"},null);
		//命中判定範圍**/
		hitRange = actionMapHandle.getDouble(new String[]{"hitrange"},3);
		//命中判定範圍**/
		hitCount = actionMapHandle.getInt(new String[]{"hitcount"},0);
		//過程中動作**/
		onTimeList = actionMapHandle.getActionMapList(new String[]{"ontime"},null);
		//最後動作**/
		onEndList = actionMapHandle.getActionMapList(new String[]{"onend"},null);
		//此動作要持續多久**/
		duration = actionMapHandle.getInt(new String[]{"duration"},100);
		//執行間隔時間**/
		period = actionMapHandle.getInt(new String[]{"period"},20);
		//如果自身死亡任務是否取消**/
		selfDead = actionMapHandle.getBoolean(new String[]{"selfdead"},true);
		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		startLocation = GetLocation.getOne(self, target, targetMap, inputLocation);

		if(startLocation != null){
			StartAction.execute(self, target, onStartList, startLocation.clone(), taskID);
			runTaskTimer(FancyCore.fancyCore, 0L, period);
		}

	}


	public void run() {
		if(selfDead && self.isDead()){
			StartAction.execute(this.self, this.target, onEndList,startLocation.clone(), taskID);
			cancel();
			return;
		}

		if(j > duration){
			StartAction.execute(this.self, this.target, onEndList,startLocation.clone(), taskID);
			cancel();
			return;
		}

		StartAction.execute(this.self, this.target, onTimeList,startLocation.clone(), taskID);

		List<LivingEntity> entityList = Radius.getRadiusLivingEntities3(self, startLocation.clone(), hitRange);
		if(entityList.size() > 0){
			if(hitCount > 0){
				if(count < hitCount){
					count++;
					for(LivingEntity livingEntity : entityList){

						Location hitLoction = livingEntity.getLocation();

						StartAction.execute(this.self, livingEntity, onHitList, hitLoction, taskID);

					}
				}
			}else {
				for(LivingEntity livingEntity : entityList){

					Location hitLoction = livingEntity.getLocation();

					StartAction.execute(this.self, livingEntity, onHitList, hitLoction, taskID);

				}
			}
		}

		j += period;
	}

}
