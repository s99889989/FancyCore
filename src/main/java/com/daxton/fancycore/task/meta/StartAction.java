package com.daxton.fancycore.task.meta;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.task.TaskAction;
import com.daxton.fancycore.task.condition.Break;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class StartAction {
	//動作包含停止
	public static void execute(LivingEntity self, LivingEntity target, String taskID, List<Map<String, String>> action_Map_List, boolean stopB){

		if(stopB){
			ClearAction.taskID(taskID);
			return;
		}

		ClearAction.taskID(taskID);

		int delay = 0;
		if(action_Map_List.size() <= 0){
			return;
		}
		for(Map<String, String> stringStringMap : action_Map_List){

			MapGetKey actionMapHandle = new MapGetKey(stringStringMap, self, target);
			String judgMent = actionMapHandle.getString(new String[]{"actiontype"}, "");
			//cd.getLogger().info(judgMent+" : "+taskID);
			if(judgMent.toLowerCase().contains("break")){
				if(!Break.valueOf(self, target, stringStringMap, taskID)){
					return;
				}
			}

			if(judgMent.toLowerCase().contains("delay")){
				int delayTicks = actionMapHandle.getInt(new String[]{"ticks","t"},0);
				delay = delay + delayTicks;
			}

			if(!judgMent.toLowerCase().contains("break") && !judgMent.toLowerCase().contains("delay")){

				new BukkitRunnable() {
					@Override
					public void run() {
						TaskAction.execute(self, target, stringStringMap, null, taskID);
					}
				}.runTaskLater(FancyCore.fancyCore, delay);

			}
		}

	}
	//動作
	public static void execute(LivingEntity self, LivingEntity target, List<Map<String, String>> action_Map_List, String taskID){
		if(action_Map_List.size() <= 0){
			return;
		}
		int delay = 0;
		for(Map<String, String> stringStringMap : action_Map_List){

			MapGetKey actionMapHandle = new MapGetKey(stringStringMap, self, target);
			String judgMent = actionMapHandle.getString(new String[]{"actiontype"}, "");

			if(judgMent.toLowerCase().contains("break")){
				if(!Break.valueOf(self, target, stringStringMap, taskID)){
					return;
				}
			}

			if(judgMent.toLowerCase().contains("delay")){
				int delayTicks = actionMapHandle.getInt(new String[]{"ticks","t"},0);
				delay = delay + delayTicks;
			}

			if(!judgMent.toLowerCase().contains("break") && !judgMent.toLowerCase().contains("delay")){

				new BukkitRunnable() {
					@Override
					public void run() {
						TaskAction.execute(self, target, stringStringMap, null, taskID);
					}
				}.runTaskLater(FancyCore.fancyCore, delay);

			}
		}

	}

	//動作包含座標
	public static void execute(LivingEntity self, LivingEntity target, List<Map<String, String>> on_Action_List, Location location, String taskID){
		if(on_Action_List.size() <= 0){
			return;
		}

		int delay = 0;
		for(Map<String, String> stringStringMap : on_Action_List){
			MapGetKey actionMapHandle = new MapGetKey(stringStringMap, self, target);
			String judgMent = actionMapHandle.getString(new String[]{"actiontype"}, "");

			if(judgMent.toLowerCase().contains("break")){
				if(!Break.valueOf(self, target, stringStringMap, taskID)){
					return;
				}
			}

			if(judgMent.toLowerCase().contains("delay")){
				int delayTicks = actionMapHandle.getInt(new String[]{"ticks","t"},0);
				delay = delay + delayTicks;
			}

			if(!judgMent.toLowerCase().contains("break") && !judgMent.toLowerCase().contains("delay")){
				new BukkitRunnable() {
					@Override
					public void run() {
						TaskAction.execute(self, target, stringStringMap, location.clone(), taskID);
					}
				}.runTaskLater(FancyCore.fancyCore, delay);

			}

		}

	}

}
